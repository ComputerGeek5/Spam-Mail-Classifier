FROM python:latest

WORKDIR /app

EXPOSE 7000

COPY requirements.txt requirements.txt

CMD ["pip3", "install", "--upgrade", "pip"]

RUN pip install -r requirements.txt

COPY . .

CMD ["python3", "-m" , "flask", "run"]