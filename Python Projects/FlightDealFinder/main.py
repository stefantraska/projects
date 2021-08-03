# This file will obtain flight deals using APIs

from data_manager import DataManager
from flight_search import FlightSearch
from flight_data import FlightData

flight_search = FlightSearch()
data_manager = DataManager()


sheet_data = data_manager.get_sheet_data()
if sheet_data[0]["iataCode"] == "":
    for row in sheet_data:
        row["iataCode"] = flight_search.find_iata(row["city"])
    data_manager.destination_data = sheet_data
    data_manager.upload_sheet_data()

for destination in sheet_data:
    flight = flight_search.get_flight_data(fly_to=destination["iataCode"])




