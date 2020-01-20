import csv
import random
import os
 
os.chdir("C:\\Users\\multicampus\\Desktop\\뉴스기사CSV") # Csv가 있는 경로 설정
 
file = open('Article_unity.csv', 'r')
line = file.readlines()
random.shuffle(line)
rcsv = csv.reader(line)
 
file_write = open('Article_shuffled.csv', 'w', newline="")
wcsv = csv.writer(file_write)
count=0
for i in rcsv:
    
    try:
        count+=1
        print(count)
        wcsv.writerow([i[0].strip(), i[1], i[2], i[3]])
    except:
        print("ERROR!",count)
        pass