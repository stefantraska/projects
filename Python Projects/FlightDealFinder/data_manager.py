import requests

url = "https://api.sheety.co/e0a58565e552bf6eec25289b1c55024b/flightDeals/prices"
api_key = ""
app_id = ""

headers = {
    "x-app-id": app_id,
    "x-app-key": api_key,
    "Content-Type": "application/json"
}

class DataManager:

    def __init__(self):
        self.destination_data = {}

    def get_sheet_data(self):
        data = requests.get(url=url, headers=headers).json()
        self.destination_data = data["prices"]
        return self.destination_data

    def upload_sheet_data(self):
        for city in self.destination_data:
            new_data = {
                "price": {
                    "iataCode": city["iataCode"]
                }
            }
            response = requests.put(url=f"{url}/{city['id']}", json=new_data)