import csv
import os
 
os.chdir("C:\\Users\\multicampus\\Desktop\\뉴스기사CSVTest") # Csv가 있는 파일 위치 수정
 
category = [
    'GS칼텍스', 'CJ제일제당','LG전자', 'LG화학', 'NH농협은행',
     'SK텔레콤', 'SK하이닉스', 'S-OIL', '네이버',
      'KT', '롯데그룹','삼성생명', '삼성전자',
      '두산','포스코','하나은행','한국전력공사',
      '신한은행','현대모비스','현대자동차'
    ]
 
file_unity = open('Article_unity.csv', 'w', newline="")
wcsv = csv.writer(file_unity)
 
count = 0
 
for category_element in category:
    file = open('NewsResult_'+category_element+'.csv', 'r', newline="")
    line = csv.reader(file)
    try:
        for line_text in line:
            count+=1
            print(count)
            wcsv.writerow([line_text[0], line_text[1],line_text[2],line_text[4]])
    except:
        print("ERROR")
        pass