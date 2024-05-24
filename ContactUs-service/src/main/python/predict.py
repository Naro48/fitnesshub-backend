import joblib
import pandas as pd
import sys

import warnings

# Suppress InconsistentVersionWarning
warnings.filterwarnings("ignore")
model = joblib.load('src/main/python/model.pkl')
preprocessor = joblib.load('src/main/python/preprocessor.pkl')
label_encoder_priority = joblib.load('src/main/python/label_encoder_priority.pkl')

message = sys.argv[1] if len(sys.argv) > 1 else "I have an issue"

new_message = [message]
new_data = pd.DataFrame({'Ticket Description': new_message})
new_X = preprocessor.transform(new_data)
predicted_priority = model.predict(new_X)

# Decode the predicted priority
predicted_priority_decoded = label_encoder_priority.inverse_transform(predicted_priority)
print(predicted_priority_decoded[0])

