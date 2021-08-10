from selenium import webdriver
import time
import requests
import numpy as np

class HomeInfo:
    def __init__(self, p, a, l):
        self.price = p
        self.address = a
        self.link = l

excel_url = "https://api.sheety.co/e0a58565e552bf6eec25289b1c55024b/zillowHouses/houseInfo"
zillow_url = "https://www.zillow.com/manhattan-new-york-ny/houses/?searchQueryState=%7B%22pagination%22%3A%7B%7D%2C%22usersSearchTerm%22%3A%22Manhattan%2C%20New%20York%2C%20NY%22%2C%22mapBounds%22%3A%7B%22west%22%3A-74.21047920019531%2C%22east%22%3A-73.73669379980468%2C%22south%22%3A40.626191262639644%2C%22north%22%3A40.933477919520115%7D%2C%22regionSelection%22%3A%5B%7B%22regionId%22%3A12530%2C%22regionType%22%3A17%7D%5D%2C%22isMapVisible%22%3Atrue%2C%22filterState%22%3A%7B%22ah%22%3A%7B%22value%22%3Atrue%7D%2C%22beds%22%3A%7B%22min%22%3A0%2C%22max%22%3A0%7D%2C%22price%22%3A%7B%22max%22%3A400000%7D%2C%22mp%22%3A%7B%22max%22%3A1300%7D%7D%2C%22isListVisible%22%3Atrue%2C%22mapZoom%22%3A11%7D"

# Address of Selenium chromedriver
address = "/Users/stefantraska/Selenium/chromedriver"
driver = webdriver.Chrome(executable_path=address)
driver.get(zillow_url)
driver.set_window_size(700, 1000)

home_info = []

prev_next_button = ""
while True:
    for scroll in range(1020, 6120, 1020):
        driver.execute_script("window.scrollTo(0, " + str(scroll) + ");")
        time.sleep(2)

    curr_prices = driver.find_elements_by_class_name("list-card-price")
    curr_addresses = driver.find_elements_by_class_name("list-card-addr")
    curr_links = driver.find_elements_by_class_name("list-card-link")

    i = 0
    for i in range(0, len(curr_prices)):
        home_info.append(HomeInfo(int(curr_prices[i].text.strip("$").replace(",", "")),
                                  curr_addresses[i].text,
                                  curr_links[i].get_attribute("href")))
    time.sleep(1)
    next_button = driver.find_element_by_css_selector('a[rel="next"]')

    if prev_next_button == "":
        prev_next_button = next_button.get_attribute("href")
    elif prev_next_button == next_button.get_attribute("href"):
        break
    prev_next_button = next_button.get_attribute("href")
    next_button.click()
    time.sleep(3)

prices = []
for i in range(0, len(home_info)):
    row = {
        "houseInfo": {
            "Price": home_info[i].price,
            "Address": home_info[i].address,
            "Link": home_info[i].link,
        }
    }
    prices.append(home_info[i].price)
    sheety = requests.post(url=excel_url, json=row)

print("Houses: " + str(len(home_info)))
print("Average price: " + str(np.mean(prices)))
print("Median price: " + str(np.median(prices)))
print("Standard Deviation: " + str(np.std(prices)))
print("25th Percentile: " + str(np.mean(prices) + np.std(prices) * -0.675))
print("75th Percentile: " + str(np.mean(prices) + np.std(prices) * 0.675))


driver.quit()