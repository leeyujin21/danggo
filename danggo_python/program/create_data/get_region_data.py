import pandas as pd
import requests
import os
from dotenv import load_dotenv


load_dotenv()

KAKAO_API_KEY = os.getenv("KAKAO_API_KEY")
KAKAO_URL = 'https://dapi.kakao.com/v2/local/search/address.json'

print(KAKAO_API_KEY)

def file_preprocessing():
    raw_data = pd.read_csv("./files/전국법정동.csv", encoding="cp949")
    
    print(raw_data.head(5))
    print(f"원본 데이터 shape: {raw_data.shape}")
    print(f"삭제일자 NaN이 아닌 데이터 수 (제거되어야 할 데이터 수): {raw_data['삭제일자'].notna().sum()}")

    # 삭제일자가 NaN인 데이터만 남기기 (현행 법정동)
    raw_data = raw_data[raw_data["삭제일자"].isna()]
    print(f"폐지된 법정동 제거 후 shape: {raw_data.shape}")

    # 필요 없는 컬럼 제거
    new_data = raw_data.drop(['과거법정동코드', '생성일자', '삭제일자', '순위'], axis=1)
    print(new_data.head())
    
    # 컬럼명 수정
    new_data.columns = ["region_cd", "sido", "sigungu", "dong_road", "ri"]
    
    # 시군구 또는 동 값이 없는 데이터 제거
    new_data = new_data.dropna(subset=['sigungu', 'dong_road'])

    return new_data

def get_lat_lon(addr):
    headers = {
        "Authorization": f"KakaoAK {KAKAO_API_KEY}"
    }
    params = {
        "query": addr
    }

    try:
        response = requests.get(KAKAO_URL, headers=headers, params=params)
        if response.status_code == 200:
            data = response.json()
            if data['documents']:
                latitude = data['documents'][0]['y'] # 위도
                longitude = data['documents'][0]['x'] # 경도
                return latitude, longitude
            else:
                return None, None
        else:
            print(f"Error: {response.status_code}")
            return None, None
    except Exception as e:
        print(f"Exception accurred: {e}")
        return None, None
    
def process_data(data):
    # 시군구 + 동 합쳐서 주소 만들기 (둘 다 NaN이 아닐 때만 실행)
    def fetch_lat_lon(row):
        if pd.notna(row["sigungu"]) and pd.notna(row["dong_road"]) and pd.isna(row["ri"]):  
            addr = f"{row['sido']} {row['sigungu']} {row['dong_road']}"
            print(addr)
            return get_lat_lon(addr)  
        else:
            return None, None

    # 위경도 컬럼 추가
    data[["latitude", "longitude"]] = data.apply(fetch_lat_lon, axis=1, result_type="expand")

    # CSV 저장
    data.to_csv("./files/region_data.csv", encoding="cp949", index=False)

    return "위경도 추가 완료 및 저장 완료"

if __name__ == '__main__':
    processed_data = file_preprocessing()
    process_data(processed_data)