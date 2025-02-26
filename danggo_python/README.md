### Python version : 3.9

---

### Overview
```
├── files/                      # 데이터 파일 폴더
│   ├── 전국법정동.csv           # 원본 데이터
│   ├── region.csv              # 컬럼 가공 및 위경도 추가 데이터
├── program                     # 프로그램 파일 폴더
│   ├── create_data             # 데이터 가공 관련 파일 폴더    
│   │   ├── get_region_data.py  # region 데이터 가공 파일
├── requirements.txt            # 필요 패키지 목록
├── .env                        # 환경 변수 (API 키 저장)
├── README.md                   # 프로젝트 설명
```

---

### 환경설정
- 아나콘다 가상환경 사용
1. 아나콘다 설치
2. 가상환경 생성 `conda create -n 가상환경이름 python=3.9`
3. 가상환경 활성화 `conda activate 가상환경이름`
4. 필수 패키지 설치 `pip install -r requirements.txt`
5. dotenv 개인 REST_API_KEY 저장 `KEY=1234` 형태 
    ```
    import os
    from dotenv import load_dotenv

    load_dotenv()
    KEY = os.getenv("KEY")
    print(KEY) # 1234
    ```

- 참고1. 아나콘다 가상환경 저장 경로 확인 `conda info --envs`
- 참고2. 필수 패키지 저장 `pip freeze > requirments.txt`