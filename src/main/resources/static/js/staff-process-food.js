const socket = new WebSocket("ws://124.58.30.200:8080/wsf/staff/processFood");

socket.onopen = () => {
    console.log("웹소켓 연결됨");
    socket.send("/refresh");

};

let remainFoodOrders;

socket.onmessage = (event) => {
    remainFoodOrders = JSON.parse(event.data);
    console.log(remainFoodOrders); // 받은 데이터 로그 출력
    renderOrderList();
};


function sendMessage() {
    const input = document.getElementById("input");
    socket.send(input.value);
    input.value = "";

    //
    selectedOrder = [];
}

function renderOrderList(){
    let selectedOrder = [];

    const submitBtn = document.querySelector(".submit-btn");





    const groupedOrders = remainFoodOrders.reduce((acc, curr) => {
        if (!acc[curr.orderId]) {
            acc[curr.orderId] = [];
        }
        acc[curr.orderId].push(curr);
        return acc;
    }, {});

    const orderList = document.querySelector('.order-list');

// 그룹화된 데이터를 이용해 HTML을 innerHTML로 동적으로 생성
    let htmlContent = '';

    Object.keys(groupedOrders).forEach(orderId => {
        const orderData = groupedOrders[orderId];
        const foodOrderId = orderData[0].foodOrderId;
        const foodOrderComplete = orderData[0].foodOrderComplete;
        // 주문을 위한 div
        let orderHtml;

        if(foodOrderComplete === 0){
            orderHtml = `
                    <div class="order-each" id="order${orderId}" data-orderId=${foodOrderId}>
                        <div class="order-id">${orderId.toString().padStart(3, '0')}</div>
                        <div class="order-menu-list">`;
        } else {
            orderHtml = `
                    <div class="order-each order-bg" id="order${orderId}" data-orderId=${foodOrderId}>
                        <div class="order-id">${orderId.toString().padStart(3, '0')}</div>
                        <div class="order-menu-list">`;
        }

        // 메뉴 항목들을 음식 이름별로 그룹화
        const menuMap = new Map(); // 음식 이름을 기준으로 그룹화

        orderData.forEach(item => {
            const foodName = item.foodName;
            if (!menuMap.has(foodName)) {
                menuMap.set(foodName, {
                    foodName,
                    foodNum: 0,
                    optionChoice: item.optionChoice
                });
            }
            menuMap.get(foodName).foodNum += item.foodNum;
        });

        // 메뉴 항목을 HTML로 추가
        menuMap.forEach(menu => {
            orderHtml += `
                    <div class="ordered-menu">
                        <div class="menu-info">
                            <div class="menu-info-name">${menu.foodName}</div>
                            <div class="menu-info-option">${menu.optionChoice === 'true' ? '소스추가' : ''}</div>
                        </div>
                        <div class="menu-qty">${menu.foodNum}</div>
                    </div>`;
        });

        orderHtml += `</div></div>`;
        htmlContent += orderHtml;
    });

    // 최종적으로 HTML을 container에 추가
    orderList.innerHTML = htmlContent;

    const orders = document.querySelectorAll('.order-each');

    orders.forEach(order => {
        order.addEventListener('click', () => {
            order.classList.toggle('selected'); // 선택 상태를 토글

            if(order.classList.contains('selected')) {
                selectedOrder.push(order.getAttribute('data-orderId'));
            } else {
                const index = selectedOrder.indexOf(order.getAttribute('data-orderId'));
                if (index > -1){
                    selectedOrder.splice(index, 1);
                }
            }
        });
    });

    submitBtn.addEventListener("click", () => {
        const choicedOrder = JSON.stringify(selectedOrder);
        socket.send(JSON.stringify({
            message: "/process",
            data: choicedOrder
        }));
        selectedOrder = [];
    })
}


const orderPageBtn = document.getElementById("order-page");
const foodOrderBtn = document.getElementById("food-page");
const beverageOrderBtn = document.getElementById("beverage-page");

orderPageBtn.addEventListener("click", () =>{
    window.location.href = "/staff/orderPage";
})
foodOrderBtn.addEventListener("click", () =>{
    window.location.href = "/staff/processFood";
})
beverageOrderBtn.addEventListener("click", () =>{
    window.location.href = "/staff/processBeverage";
})