# java-convenience-store-precourse

### 프리코스 4주 차 [오예현]

# 🔆 편의점
구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

## ✅ 기능 요구사항
- 최종 결제 금액 : 사용자가 입력한 `상품의 가격` x `수량`+ `프로모션/멤버십 할인` 적용  


- 영수증 : `구매 내역`, `산출 금액 정보`
    - 영수증 출력 후, `추가 구매` or `종료` 선택


- 사용자가 잘못된 값 입력한 경우
  >1. `IllegalArgumentException` 발생
  >2. `[ERROR]`로 시작하는 에러 메시지 출력
  >3. 해당 부분부터 입력 다시 받음

---

- 재고 관리 
    - 각 상품의 `재고 수량`을 고려하여 결제 가능 여부 확인
    - 고객이 상품을 구매할 때마다, 구매한 수량만큼 `해당 상품의 재고에서 차감`하여 수량 관리
    - 재고를 차감하며 시스템은 `최신 재고 상태`를 유지, 다음 고객이 구매할 때 정확한 재고 정보를 제공


- 프로모션 할인
    - 오늘 날짜가 `프로모션 기간 안`인 경우만 할인 적용
    - 프로모션은 `N개 구매 시 1개 무료 증정` 형태로 진행
    - 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 `여러 프로모션 적용 불가`
    - 프로모션 혜택은 `프로모션 재고 내`에서만 적용
    - 프로모션 기간 중이면 `프로모션 재고 우선 차감`하고, 이후 일반 재고 사용
    - 고객이 프로모션 제공 상품을 해당 수량보다 적게 가져온 경우, `필요한 수량을 추가로 가져오면 혜택 받을 수 있음`을 안내
    - 고객이 원하는 상품의 프로모션 재고가 부족하여 일부 수량을 일반 결제해야 하는 경우, `일부 수량에 대해 정가로 결제됨`을 안내


- 멤버십 할인
    - 프로모션 적용 후 남은 금액에 대해 `30%` 멤버십 할인 적용
    - 멤버십 할인 최대 한도는 `8,000원`


- 영수증 출력
    - 고객의 구매 내역, 할인을 요약
    - 영수증 항목
        - 구매 상품 내역 : `상품명`, `수량`, `가격`
        - 증정 상품 내역 : 프로모션으로 무료 제공된 `증정 상품의 목록`
        - 금액 정보 
            - 총구매액 : 구매한 상품의 `총 수량`과 `총 금액`
            - 행사할인 : `프로모션 할인된 금액`
            - 멤버십할인 : `멤버십 할인된 금액`
            - 내실돈 : `최종 결제 금액`
    - 영수증의 구성 요소를 `정렬`하여 금액과 수량을 쉽게 확인

## ✅ 입출력 요구사항
- 입력
    - `상품 목록`, `행사 목록`을 파일을 통해 불러옴
        - `src/main/resources/products.md`, `src/main/resources/promotions.md`
    - `구매할 상품`과 `수량` : `[상품명-수량],[상품명-수량]` 형식
        ```
        [콜라-10],[사이다-3]
        ```
    - 필요한 수량을 추가로 가져오면 혜택 받을 수 있음이 안내된 후, `수량만큼 추가 여부`를 입력  
        - `Y` : 증정 받을 수 있는 상품 추가
        - `N` : 증정 받을 수 있는 상품 추가 안 함
        ```
        Y
        ```
    - 일부 수량에 대해 정가로 결제됨이 안내된 후, 일부 수량에 대해 `정가로 결제할지 여부`를 입력
        - `Y` : 일부 수량에 대해 정가로 결제
        - `N` : 정가로 결제할 수량 제외한 후 결제
        ```
        Y
        ```
    - `멤버십 할인 적용 여부`를 입력
        - `Y` : 멤버십 할인 적용
        - `N` : 멤버십 할인 적용 안 함
        ```
        Y
        ```
    - `추가 구매 여부`를 입력
        - `Y` : 업데이트 된 최신 재고 상태를 확인 후 추가 구매 진행
        - `N` : 구매 종료
        ```
        Y
        ```
- 출력
    - `환영 인사`, `상품명`, `가격`, `프로모션 이름`, `재고` 안내
        - 재고가 0개라면 `재고 없음`을 출력
        ```
        안녕하세요. W편의점입니다.
        현재 보유하고 있는 상품입니다.
        
        - 콜라 1,000원 10개 탄산2+1
        - 콜라 1,000원 10개
        - 사이다 1,000원 8개 탄산2+1
        - 사이다 1,000원 7개
        - 오렌지주스 1,800원 9개 MD추천상품
        - 오렌지주스 1,800원 재고 없음
        - 탄산수 1,200원 5개 탄산2+1
        - 탄산수 1,200원 재고 없음
        - 물 500원 10개
        - 비타민워터 1,500원 6개
        - 감자칩 1,500원 5개 반짝할인
        - 감자칩 1,500원 5개
        - 초코바 1,200원 5개 MD추천상품
        - 초코바 1,200원 5개
        - 에너지바 2,000원 5개
        - 정식도시락 6,400원 8개
        - 컵라면 1,700원 1개 MD추천상품
        - 컵라면 1,700원 10개

        구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
        ```
    - `필요한 수량을 추가로 가져오면 혜택 받을 수 있음`에 대한 안내 메시지 출력
        ```
        현재 {상품명}은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
        ```
    - `일부 수량에 대해 정가로 결제됨`에 대한 안내 메시지 출력
        ```
        현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
        ```
    - `멤버십 할인 적용 여부`에 대한 안내 메시지 출력
        ```
        멤버십 할인을 받으시겠습니까? (Y/N)
        ```
    - `구매 상품 내역`, `증정 상품 내역`, `금액 정보` 출력
        ```
        ==============W 편의점================
        상품명		수량	금액
        콜라		3 	3,000
        에너지바 		5 	10,000
        =============증	정===============
        콜라		1
        ====================================
        총구매액		8	13,000
        행사할인			-1,000
        멤버십할인			-3,000
        내실돈			 9,000
        ```
    - `추가 구매 여부`에 대한 안내 메시지 출력
        ```
        감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
        ```
    - `오류 메시지` 출력
        - 구매할 상품과 수량 형식이 올바르지 않은 경우
        ```
        [ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
        ```
        - 존재하지 않는 상품을 입력한 경우
        ```
        [ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
        ```
        - 구매 수량이 재고 수량을 초과한 경우
        ```
        [ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
        ```
        - 기타 잘못된 입력의 경우
        ```
        [ERROR] 잘못된 입력입니다. 다시 입력해 주세요.
        ```

- 실행 결과 예시
```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]

멤버십 할인을 받으시겠습니까? (Y/N)
Y 

==============W 편의점================
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
=============증	정===============
콜라		1
====================================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10]

현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
N

==============W 편의점================
상품명		수량	금액
콜라		10 	10,000
=============증	정===============
콜라		2
====================================
총구매액		10	10,000
행사할인			-2,000
멤버십할인			-0
내실돈			 8,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 재고 없음 탄산2+1
- 콜라 1,000원 7개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[오렌지주스-1]

현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

==============W 편의점================
상품명		수량	금액
오렌지주스		2 	3,600
=============증	정===============
오렌지주스		1
====================================
총구매액		2	3,600
행사할인			-1,800
멤버십할인			-0
내실돈			 1,800

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```
## ☑️ 구현할 기능 목록
- `Application` 클래스

- [ ] : 편의점을 오픈하는 함수


- `PromotionParser`

- [ ] : 프로모션 정보 파일을 받아 Promotion 객체 생성


- `ProductParser` 

- [ ] : 상품 목록 파일을 받아 Product 객체와 ProductStratus 객체 생성


- `PurchaseProduct` 클래스

- [ ] : 구매할 물건의 이름, 수량, 프로모션 유무를 갖는 객체

- [ ] : 프로모션 상품이면 true 로 변경하는 함수


- `PurchaseList` 클래스

- [ ] : 상품 재고 관리를 수행하는 함수

- [ ] : 구매 상품의 프로모션 상품 수, 일반 상품 수를 저장하는 함수


- `Product` 클래스

- [ ] : 상품명, 가격을 갖는 객체


- `DateRange` 클래스

- [ ] : startDate, endDate를 갖는 객체

- [ ] : 프로모션 기간 안인지 확인하는 함수


- `ProductStatus` 클래스

- [ ] : product, 재고, 프로모션을 갖는 객체

- [ ] : 판매하여 재고 감소하는 함수


- `Promotion` 클래스

- [ ] : 프로모션명, buyProduct, getProduct, DateRange 를 갖는 객체

- [ ] : 구매해야 하는 단위를 알려주는 함수


- `PromotionManager` 클래스

- [ ] : 프로모션명을 키, promotion 을 값으로 갖는 Map


- `ProductManager` 클래스

- [ ] : 상품명을 키, product 를 값으로 갖는 Map


- `MembershipService` 클래스

- [ ] : 멤버십 할인 계산하는 함수


- `ReceiptService` 클래스

- [ ] : 구매 상품 내역을 출력하는 함수

- [ ] : 증정 상품 내역을 출력하는 함수

- [ ] : 금액 정보를 출력하는 함수


- `counterService` 클래스

- [ ] : 구매할 상품과 수량을 가지고 상품 재고 관리를 실행


- `StoreInputView` 클래스

- [ ] : 구매할 상품 입력

- [ ] : 수량 입력

- [ ] : 혜택 수량만큼 추가 여부 입력

- [ ] : 정가로 결제할지 여부 입력

- [ ] : 멤버십 할인 적용 여부 입력

- [ ] : 추가 구매 여부 입력


- `StoreOutputView` 클래스

- [ ] : 환영 인사 출력

- [ ] : 상품목록 출력

- [ ] : 혜택 수량만큼 추가 여부 문구 출력

- [ ] : 정가로 결제할지 여부 문구 출력

- [ ] : 멤버십 할인 적용 여부 문구 출력

- [ ] : 추가 구매 여부 문구 출력

- [ ] : 영수증 출력


- `StoreController` 클래스
1. 환영 인사 출력
2. 상품 목록 출력
3. 상품명, 수량 입력받아 CheckoutCounter 실행
4. 관련 안내 문구 실행
5. 멤버십 할인 문구 출력
6. 멤버십 할인 실행
7. 영수증 출력

## ☑️ 프료그램 구조