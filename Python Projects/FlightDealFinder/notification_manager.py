from twilio.rest import Client

twilio_api_key = ""
sid = ""
token = ""
client = Client(sid, token)


class NotificationManager:

    def __init__(self):
        pass

    def send_message(self, msg):
        message = client.messages \
            .create(
            body=msg,
            from_='+',
            to='+'
        )


