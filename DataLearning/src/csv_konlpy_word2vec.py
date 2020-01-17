from konlpy.tag import Twitter
from gensim.models import Word2Vec
import csv

twitter = Twitter()

file = open("C:\\Users\\multicampus\\Desktop\\뉴스기사CSVTest\\Article_unity.csv", 'r')
line = csv.reader(file)
token = []
embeddingmodel = []

for i in line:
    content = i[2]  # csv에서 뉴스 제목 또는 뉴스 본문 column으로 변경
    sentence = twitter.pos(content, norm=True, stem=True)
    temp = []
    temp_embedding = []
    all_temp = []
    for k in range(len(sentence)):
        temp_embedding.append(sentence[k][0])
        temp.append(sentence[k][0] + '/' + sentence[k][1])
    all_temp.append(temp)
    embeddingmodel.append(temp_embedding)
    category = i[0]  # csv에서 category column으로 변경
    category_number_dic = {'CJ제일제당': 0, 'GS칼텍스': 1, 'KT': 2, 'LG전자': 3, 'LG화학': 4, 'NH농협은행': 5, 'SK텔레콤': 6, 'SK하이닉스': 7, 'S-OIL': 8, '네이버': 9, '두산': 10, '롯데그룹': 11, '삼성생명': 12, '삼성전자': 13, '신한은행': 14, '포스코': 15, '하나은행': 16, '한국전력공사': 17, '현대모비스': 18, '현대자동차': 19}
    all_temp.append(category_number_dic.get(category))
    token.append(all_temp)
print("토큰 처리 완료")


embeddingmodel = []
for i in range(len(token)):
    temp_embeddingmodel = []
    for k in range(len(token[i][0])):
        temp_embeddingmodel.append(token[i][0][k])
    embeddingmodel.append(temp_embeddingmodel)
embedding = Word2Vec(embeddingmodel, size=300, window=5, min_count=10, iter=5, sg=1, max_vocab_size=360000000)
embedding.save('post.embedding')

