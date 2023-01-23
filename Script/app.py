from flask import Flask, request
import pandas as pd
import mysql.connector
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

# connection = mysql.connector.connect(
#     host = "localhost",
#     user = "root",
#     password = "",
#     database='spam_db',
# )

connection = mysql.connector.connect(
    host = "spam-classifier-db",
    user = "root",
    password = "",
    database='spam_db',
)

data = pd.read_sql_query("SELECT * FROM tbl_mail", connection)
data = data[data['is_spam'].notnull()]

X = data['message']
y = data['is_spam']

cv = CountVectorizer()
X = cv.fit_transform(X)
x_train, x_test,y_train, y_test = train_test_split(X, y, test_size=0.1, random_state=42)
model = MultinomialNB()
model.fit(x_train, y_train)

@app.get('/classify')
def classify():
    args = request.args
    mail = [args.get("message")]
    vect = cv.transform(mail).toarray()
    my_prediction = model.predict(vect)

    if my_prediction[0] == 1:
        return {
            "spam": True,
            "message": "Mail classified successfully",
            "status": 200
        }
    else:
        return {
            "spam": False,
            "message": "Mail classified successfully",
            "status": 200
        }

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=7000)
