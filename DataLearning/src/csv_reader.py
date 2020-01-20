import csv
import os
 
os.chdir("C:\\Users\\multicampus\\Desktop\\뉴스기사CSV")
 
file = open('Article_unity.csv', 'r', encoding='euc-kr')
line = csv.reader(file)
 
for i in line:
    print(i[0], i[1], i[2])