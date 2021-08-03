import requests
from datetime import datetime
#
#
#
# This program takes in exercise, gets data from an API, then
# adds data to https://docs.google.com/spreadsheets/d/1U4Uz_f9ayXVw48IlyOLRNhUSC3U7ypesBFonuOlHdvU/edit#gid=0
#
#
#

api_key = ""
app_id = ""
url = "https://trackapi.nutritionix.com/v2/natural/exercise"


sheety_url = "https://api.sheety.co/e0a58565e552bf6eec25289b1c55024b/workouts/workouts"

headers = {
    "x-app-id": app_id,
    "x-app-key": api_key,
    "Content-Type": "application/json"
}

workout = input("What Exercise?  ")

post_request = {
    "query": workout,
    "gender": "male",
    "weight_kg": 72.5,
    "height_cm": 160,
    "age": 19
}

response = requests.post(url=url, json=post_request, headers=headers).json()
for workout in response["exercises"]:
    date = str(datetime.today())
    time = str(datetime.now().strftime("%H:%M:%S"))
    exercise = workout["name"]
    duration = workout["duration_min"]
    calories = workout["nf_calories"]
    row = {
        "workout": {
            "date": date,
            "time": time,
            "exercise": exercise.title(),
            "duration": duration,
            "calories": calories
        }
    }
    sheety = requests.post(url=sheety_url, json=row)

print(sheety.text)