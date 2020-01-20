import time
import tensorflow as tf
import numpy as np
import Bi_LSTM as Bi_LSTM
import Word2Vec as Word2Vec
import csv
from konlpy.tag import Twitter
import os

twitter = Twitter()
W2V = Word2Vec.Word2Vec()

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

tokens = np.array(token)
print("token 처리 완료")
print("train_data 최신 버전인지 확인")
train_X = tokens[:, 0]
train_Y = tokens[:, 1]

train_Y_ = W2V.One_hot(train_Y)  # Convert to One-hot
train_X_ = W2V.Convert2Vec("C:\\Users\\multicampus\\Python Test\\post.embedding",train_X)  # import word2vec model where you have trained before

Batch_size = 128
Total_size = len(train_X)
Vector_size = 300
seq_length = [len(x) for x in train_X]
Maxseq_length = max(seq_length)
learning_rate = 0.001
lstm_units = 128
num_class = 12
training_epochs = 5
keep_prob = 0.75

X = tf.placeholder(tf.float32, shape = [None, Maxseq_length, Vector_size], name = 'X')
Y = tf.placeholder(tf.float32, shape = [None, num_class], name = 'Y')
seq_len = tf.placeholder(tf.int32, shape = [None])

BiLSTM = Bi_LSTM.Bi_LSTM(lstm_units, num_class, keep_prob)

with tf.variable_scope("loss", reuse = tf.AUTO_REUSE):
    logits = BiLSTM.logits(X, BiLSTM.W, BiLSTM.b, seq_len)
    loss, optimizer = BiLSTM.model_build(logits, Y, learning_rate)

prediction = tf.nn.softmax(logits)
correct_pred = tf.equal(tf.argmax(prediction, 1), tf.argmax(Y, 1))
accuracy = tf.reduce_mean(tf.cast(correct_pred, tf.float32))

init = tf.global_variables_initializer()

total_batch = int(Total_size / Batch_size)

print("Start training!")

modelName = "BiLSTM.model"
saver = tf.train.Saver() # 모델과 파라미터를 저장하고 불러올 수 있게 만들어주는 API


with tf.Session() as sess:

    start_time = time.time()
    sess.run(init)
    train_writer = tf.summary.FileWriter('Bidirectional_LSTM', sess.graph)
    i = 0
    for epoch in range(training_epochs):

        avg_acc, avg_loss = 0. , 0.
        for step in range(total_batch):

            train_batch_X = train_X_[step*Batch_size : step*Batch_size+Batch_size]
            train_batch_Y = train_Y_[step*Batch_size : step*Batch_size+Batch_size]
            batch_seq_length = seq_length[step*Batch_size : step*Batch_size+Batch_size]
            
            train_batch_X = W2V.Zero_padding(train_batch_X, Batch_size, Maxseq_length, Vector_size)
            
            sess.run(optimizer, feed_dict={X: train_batch_X, Y: train_batch_Y, seq_len: batch_seq_length})
            # Compute average loss
            loss_ = sess.run(loss, feed_dict={X: train_batch_X, Y: train_batch_Y, seq_len: batch_seq_length})
            avg_loss += loss_ / total_batch
            
            acc = sess.run(accuracy , feed_dict={X: train_batch_X, Y: train_batch_Y, seq_len: batch_seq_length})
            avg_acc += acc / total_batch
            print("epoch : {:02d} step : {:04d} loss = {:.6f} accuracy= {:.6f}".format(epoch+1, step+1, loss_, acc))

        summary = sess.run(BiLSTM.graph_build(avg_loss, avg_acc))       
        train_writer.add_summary(summary, i)
        i += 1

    duration = time.time() - start_time
    minute = int(duration / 60)
    second = int(duration) % 60
    print("%dminutes %dseconds" % (minute,second))
    save_path = saver.save(sess, os.getcwd())

    train_writer.close()
    print('save_path',save_path)
