import requests
from twilio.rest import Client

# This program checks to see if a stock has a change in over 1%, and if so, sends a text to the user
# of the top news for the company

STOCK = "GE"
COMPANY_NAME = "General Electric"
stock_api_key = ""
news_api_key = ""
twilio_api_key = ""
sid = ""
token = ""

stock_parameters = {
    "function": "TIME_SERIES_DAILY",
    "symbol": STOCK,
    "apikey": stock_api_key
}

stock_url = 'https://www.alphavantage.co/query'
news_url = "https://newsapi.org/v2/everything"
data = requests.get(stock_url, params=stock_parameters).json()["Time Series (Daily)"]
yesterday_close_price = float([value for (key, value) in data.items()][0]["4. close"])
before_yesterday_close_price = float([value for (key, value) in data.items()][1]["4. close"])
difference = yesterday_close_price - before_yesterday_close_price
percent_of_stock = difference/before_yesterday_close_price * 100
percent_of_stock_string = str(round(percent_of_stock, 2)) + "%"

news_parameters = {
    "q": COMPANY_NAME,
    "from": [key for (key, value) in data.items()][1],
    "sortBy": "popularity",
    "apiKey": news_api_key
}
if abs(percent_of_stock) > 1:
    all_news = requests.get(news_url, params=news_parameters).json()["articles"]
    first_article = all_news[0]
    title = first_article["title"]
    brief = first_article["description"]

    second_article = all_news[1]
    title2 = second_article["title"]
    brief2 = second_article["description"]

    third_article = all_news[2]
    title3 = third_article["title"]
    brief3 = third_article["description"]
    client = Client(sid, token)
    message = COMPANY_NAME + " News" + "(Changed " + percent_of_stock_string + "):\n\n" + title + "\n--------\n" \
              + brief + "\n\n" + title2 + "\n--------\n" + brief2 \
              + "\n\n" + title3 + "\n--------\n" + brief3
    message = client.messages \
        .create(
        body=message,
        # Add phone numbers here
        from_='+',
        to='+'
    )