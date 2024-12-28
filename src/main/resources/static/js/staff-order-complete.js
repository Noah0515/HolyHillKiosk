let orderIds;

const urlParams = new URLSearchParams(window.location.search);
const foodOrderId = urlParams.get('foodOrderId');
const beverageOrderId = urlParams.get('beverageOrderId');

console.log("음식 주문 ID:", foodOrderId);
console.log("음료 주문 ID:", beverageOrderId);


function updateMessage(jsonResponse) {
    orderIds = JSON.parse(jsonResponse);
    console.log(orderIds);
}

function renderOrderIds(){
    const infoBody = document.querySelector(".info-body");
    let orderIdHTML = '';
    if(foodOrderId !== "null"){
        orderIdHTML += `<div class="order-id">음식 주문번호: ${foodOrderId}</div>`;
    }
    if(beverageOrderId !== "null"){
        orderIdHTML += `<div class="order-id">음료 주문번호: ${beverageOrderId}</div>`;
    }
    orderIdHTML += `
        <div class="guide">주문번호를 적어주세요.<br>옆에 종이가 구비되어있습니다.</div>
    `
    infoBody.innerHTML = orderIdHTML;
}
renderOrderIds();

const completeBtn = document.querySelector(".complete-btn");

completeBtn.addEventListener("click", () =>{
    console.log("주문번호 확인");

    window.location.href = "/staff/orderPage";
})

let countdownTime = 15;

// 카운트다운을 표시할 요소
const countdownElement = document.querySelector('.countdown');

// 카운트다운 시작
const countdownInterval = setInterval(() => {
    // 카운트다운 시간이 0보다 크면 계속 카운트다운을 표시
    if (countdownTime > 0) {
        countdownElement.textContent = `${countdownTime}초 후에 주문화면으로 돌아갑니다`;
        countdownTime--;  // 카운트다운 감소
    } else {
        // 카운트다운 시간이 끝나면 페이지 이동
        clearInterval(countdownInterval);  // 카운트다운 멈추기
        window.location.href = "/staff/orderPage";  // 주문 페이지로 이동
    }
}, 1000);  // 1000ms(1초)마다 실행

const orderPageBtn = document.getElementById("order-page");
const foodOrderBtn = document.getElementById("food-btn");
const beverageOrderBtn = document.getElementById("beverage-btn");

orderPageBtn.addEventListener("click", () =>{
    window.location.href = "/staff/orderPage";
})
foodOrderBtn.addEventListener("click", () =>{
    window.location.href = "/staff/processFood";
})
beverageOrderBtn.addEventListener("click", () =>{
    window.location.href = "/staff/processBeverage";
})