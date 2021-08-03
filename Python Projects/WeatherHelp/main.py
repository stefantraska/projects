# This program checkks the weather and sends a text whether to bring an umbrella or not.

import requests
from twilio.rest import Client

api_key = ""
sid = ""
token = ""

parameters = {
    "lat": 39.575290,
    "lon": -76.576880,
    "appid": api_key,
    "exclude": "current,minutely,daily"
}

data = requests.get(url="https://api.openweathermap.org/data/2.5/onecall", params=parameters).json()
hours = int(input("How many hours are you out for?\n"))
bring_umbrella = False
for num in range(0, hours-1):
    if data["hourly"][num]["weather"][0]["id"] < 700:
        bring_umbrella = True

if bring_umbrella:
    client = Client(sid, token)
    message = client.messages \
        .create(
        body="It's gonna rain today. Bring your umbrella!",
        from_='+',
        to='+'
    )
else:
    client = Client(sid, token)
    message = client.messages \
        .create(
        body="It's not gonna rain today. No umbrella needed!",
        from_='+',
        to='+'
    )