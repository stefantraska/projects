from datetime import date, datetime, timedelta

import requests
from flight_data import FlightData


api_key = ""
search_url = "https://tequila-api.kiwi.com/locations/query"
query_url = "https://tequila-api.kiwi.com/v2/search"
headers = {
            "apikey": api_key
        }

tomorrow = (datetime.now() + timedelta(days=10)).strftime("%d/%m/%Y")
six_month_from_today = (datetime.now() + timedelta(days=(6 * 30))).strftime("%d/%m/%Y")

class FlightSearch:


    def find_iata(self, city_name):
        parameters = {
            "term": city_name,
            "location_types": "city"
        }
        return requests.get(url=search_url, headers=headers, params=parameters).json()["locations"][0]["code"]

    def get_flight_data(self, fly_to):
        parameters = {
            "fly_from": "NYC",
            "fly_to": fly_to,
            "date_from": tomorrow,
            "date_to": six_month_from_today,
            "flight_type": "round",
            "adults": 2,
            "nights_in_dst_from": 2,
            "nights_in_dst_to": 28,
            "max_stopovers": 0,
            "curr": "USD"
        }
        try:
            response = requests.get(url=query_url, headers=headers, params=parameters).json()
            data = response["data"][0]
        except IndexError:
            print(f"No flights found for {fly_to}.")
            return None

        flight_data = FlightData(
            price=data["price"],
            origin_city=data["route"][0]["cityFrom"],
            origin_airport=data["route"][0]["flyFrom"],
            destination_city=data["route"][0]["cityTo"],
            destination_airport=data["route"][0]["flyTo"],
            out_date=data["route"][0]["local_departure"].split("T")[0],
            return_date=data["route"][1]["local_departure"].split("T")[0]
        )
        print(f"{flight_data.destination_city}: £{flight_data.price}")
        return flight_data
