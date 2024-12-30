/*
음료, 음식 버튼 선택 부분
 */
const beverageButton = document.getElementById('beverage-btn');
const foodButton = document.getElementById('food-btn');

// 항목들
const beverageItems = document.getElementById('beverage-menu');
const foodItems = document.getElementById('food-menu');

/*
메뉴 항목
 */
let foodMenus = [
    {name: "소떡소떡", menuCode: 1, menuOption: true, qty1: 0, qty2: 0, image: "../img/소떡소떡.png"},
    {name: "길거리토스트", menuCode: 2, menuOption: true, qty1: 0, qty2: 0, image: "../img/길거리토스트.png"},
    {name: "어묵", menuCode: 3, menuOption: false, qty1:0, qty2: 0, image: "../img/어묵.png"}
];
let beverageMenus = [
    {name: "핸드드립(아이스)", menuCode: 1, menuOption: false, qty1: 0, qty2: 0, image: "../img/핸드드립_아이스.png"},
    {name: "핸드드립(핫)", menuCode: 2, menuOption: false, qty1: 0, qty2: 0, image: "../img/핸드드립_핫.png"},
    {name: "아이스티", menuCode: 3, menuOption: false, qty1: 0, qty2: 0, image: "../img/아이스티.png"},
    {name: "유자차", menuCode: 4, menuOption: false, qty1: 0, qty2: 0, image: "../img/유자차.png"},
    {name: "율무차", menuCode: 5, menuOption: false, qty1: 0, qty2: 0, image: "../img/율무차.png"}
];


// 음료 항목을 동적으로 생성하는 함수
function renderBeverageMenu() {
    console.log("음료 랜더링 시작")
    const menuListBoard = document.querySelector("#beverage-menu .menu-list-beverage");
    let menuHTML = ''; // 생성될 HTML 문자열을 담을 변수

    // 각 메뉴 항목을 반복문으로 처리
    beverageMenus.forEach(menu => {
        menuHTML += `
            <div class="col mb-4">
                <div id="${menu.name}" class="card h-90 choice-btn" data-menu-name="${menu.name}" data-menu-type="beverage" data-menu-code="${menu.menuCode}" data-menu-option="${menu.menuOption}">
                    <img class="card-img-top" src="${menu.image}" alt="${menu.alt}" />
                    <div class="card-body p-3">
                        <div class="text-center">
                            <h5 class="fw-bolder">${menu.name}</h5>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });

    // 동적으로 생성된 HTML을 menu-list-board에 삽입
    menuListBoard.innerHTML = menuHTML;
    console.log("음료 랜더링 완료");
}

// 음식 항목을 동적으로 생성하는 함수
function renderFoodMenu() {
    console.log("음식 랜더링 시작")
    const menuListBoard = document.querySelector("#food-menu .menu-list-food");
    let menuHTML = ''; // 생성될 HTML 문자열을 담을 변수

    // 각 메뉴 항목을 반복문으로 처리
    foodMenus.forEach(menu => {
        menuHTML += `
            <div class="col mb-4">
                <div id="${menu.name}" class="card h-90 choice-btn" data-menu-name="${menu.name}" data-menu-type="food" data-menu-code="${menu.menuCode}" data-menu-option="${menu.menuOption}">
                    <img class="card-img-top" src="${menu.image}" alt="${menu.alt}" />
                    <div class="card-body p-3">
                        <div class="text-center">
                            <h5 class="fw-bolder">${menu.name}</h5>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });

    // 동적으로 생성된 HTML을 menu-list-board에 삽입
    menuListBoard.innerHTML = menuHTML;
    console.log("음식 랜더링 완료");
}

//선택내역들 동적으로 생성
function renderChoicedMenuList(){
    console.log("담은내역 랜더링 시작");
    const choicedMenuList = document.querySelector(".choiced-menu-list");
    let listHTML = '';

    foodMenus.forEach(menu => {
        if(menu.qty1 > 0){
            listHTML += `
                <div class="choiced-menu-info">
                    <div class="added-menu">
                        <div class="container added-menu-name">${menu.name}</div>
                        <div class="added-menu-count" >
                            <button class="choiced-decrease-qty menu-count-btn" data-menu-type="food" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty1}" data-menu-option="false">-</button>
                            <span class="menu-qty" style="font-size: 1.5rem;">${menu.qty1}</span>
                            <button class="choiced-increase-qty menu-count-btn" data-menu-type="food" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty1}" data-menu-option="false">+</button>
                        </div>
                    </div>
                </div>
            `;

        }

        if(menu.qty2 > 0){
            listHTML += `
                <div class="choiced-menu-info">
                    <div class="added-menu">
                        <div class="container added-menu-name">${menu.name}</div>
                        <div class="container add-option">소스추가</div>
                        <div class="added-menu-count" >
                            <button class="choiced-decrease-qty menu-count-btn" data-menu-type="food" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty2}" data-menu-option="true">-</button>
                            <span class="menu-qty" style="font-size: 1.5rem;">${menu.qty2}</span>
                            <button class="choiced-increase-qty menu-count-btn" data-menu-type="food" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty2}" data-menu-option="true">+</button>
                        </div>
                    </div>
                </div>
            `;
        }
    })

    beverageMenus.forEach(menu => {
        if(menu.qty1 > 0){
            listHTML += `
                <div class="choiced-menu-info">
                    <div class="added-menu">
                        <div class="container added-menu-name">${menu.name}</div>
                        <div class="added-menu-count" >
                            <button class="choiced-decrease-qty menu-count-btn" data-menu-type="beverage" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty1}" data-menu-option="false">-</button>
                            <span class="menu-qty" style="font-size: 1.5rem;">${menu.qty1}</span>
                            <button class="choiced-increase-qty menu-count-btn" data-menu-type="beverage" data-menu-code="${menu.menuCode}" data-menu-qty="${menu.qty1}" data-menu-option="false">+</button>
                        </div>
                    </div>
                </div>
            `;

        }
    })

    choicedMenuList.innerHTML = listHTML;

    // 메뉴 수량 변경 기능 추가
    const decreaseBtns = document.querySelectorAll('.choiced-decrease-qty');
    const increaseBtns = document.querySelectorAll('.choiced-increase-qty');

    decreaseBtns.forEach(button => {
        button.addEventListener('click', function() {
            const menuCode = button.getAttribute('data-menu-code');
            const menuType = button.getAttribute('data-menu-type');
            const qtySpan = button.nextElementSibling; // 수량이 표시된 span
            const menuOption = button.getAttribute('data-menu-option');
            let qty = parseInt(qtySpan.textContent);
            if (qty > 0) {
                qty--; // 수량 감소
                qtySpan.textContent = qty;
                updateMenuQty(menuCode, menuType, qty, menuOption);
            }
            // 수량이 0이 되면 해당 메뉴 항목을 화면에서 제거
            if (qty === 0) {
                removeMenuFromList(menuCode, menuType, menuOption);
            }
        });
    });

    increaseBtns.forEach(button => {
        button.addEventListener('click', function() {
            const menuCode = button.getAttribute('data-menu-code');
            const menuType = button.getAttribute('data-menu-type');
            const qtySpan = button.previousElementSibling; // 수량이 표시된 span
            const menuOption = button.getAttribute('data-menu-option');
            let qty = parseInt(qtySpan.textContent);
            qty++; // 수량 증가
            qtySpan.textContent = qty;
            updateMenuQty(menuCode, menuType, qty, menuOption);
        });
    });
}

function updateMenuQty(menuCode, menuType, newQty, menuOption) {
    // 해당 메뉴의 수량을 업데이트 (foodMenus 또는 beverageMenus)
    const menus = menuType === 'food' ? foodMenus : beverageMenus;
    const menu = menus.find(item => item.menuCode === parseInt(menuCode));
    if (menu) {
        if (menuType === 'food') {
            if(menuOption === "false"){
                menu.qty1 = newQty; // qty1의 수량 업데이트
                console.log(menu.qty1);
            } else {
                menu.qty2 = newQty;
                console.log(menu.qty2);
            }
        } else {
            menu.qty1 = newQty; // qty2의 수량 업데이트
            console.log(menu.qty1);
        }
    }
}

// 메뉴를 화면에서 제거하는 함수
function removeMenuFromList(menuCode, menuType, menuOption) {
    const menuItems = document.querySelectorAll(`.choiced-menu-info[data-menu-code="${menuCode}"][data-menu-type="${menuType}"]`);

    menuItems.forEach(item => {
        item.remove(); // 해당 메뉴 항목을 DOM에서 제거
    });

    // 메뉴 배열에서 해당 메뉴를 제거
    const menus = menuType === 'food' ? foodMenus : beverageMenus;
    const menuIndex = menus.findIndex(item => item.menuCode === parseInt(menuCode));

    if (menuIndex !== -1) {
        if (menuType === 'food' && menuOption === "true") {
            menus[menuIndex].qty2 = 0; // qty2 수량을 0으로 설정
        } else {
            menus[menuIndex].qty1 = 0; // qty1 수량을 0으로 설정
        }
    }
    renderChoicedMenuList();
}

renderFoodMenu();
renderBeverageMenu();
renderChoicedMenuList();
// 기본적으로 음식 항목을 보이게 설정
foodItems.style.display = 'block';
beverageItems.style.display = 'none';

// 음료 버튼 클릭 시
beverageButton.addEventListener('click', () => {
    console.log("음료버튼 눌림");
    // 음료 항목 보이기, 음식 항목 숨기기
    beverageItems.style.display = 'block';
    foodItems.style.display = 'none';

});

// 음식 버튼 클릭 시
foodButton.addEventListener('click', () => {
    console.log("음식버튼 눌림");
    // 음식 항목 보이기, 음료 항목 숨기기
    foodItems.style.display = 'block';
    beverageItems.style.display = 'none';

});


/*
모달 부분
 */
const modal = document.querySelector(".modal");
const choiceBtn = document.querySelectorAll(".choice-btn");
const closeModalBtn = document.getElementById("close-modal-btn");
const modalTitle = document.getElementById("modal-title");

// 버튼 클릭 시 모달 열기
choiceBtn.forEach((card) => {
    console.log("메뉴카드 눌림");
    card.addEventListener("click", async () => {
        const menuName = card.getAttribute("data-menu-name");
        const menuImg = card.querySelector("img").src;
        const menuOption = card.getAttribute("data-menu-option") === "true";
        const menuType = card.getAttribute("data-menu-type");
        const menuCode = card.getAttribute("data-menu-code");
        // 모달 내용 업데이트
        const modalHeader = document.querySelector(".modal-header");
        const modalBody = document.querySelector(".modal-body");
        const modalFooter = document.querySelector(".modal-footer");

        modalTitle.textContent = menuName;

        modalBody.innerHTML = `
                <img src="${menuImg}" class="modal-img" alt="${menuName}"/>
                ${menuOption ? `
                    <div class="modal-option">
                        <label class="modal-option-label">
                            <input type="radio" class="modal-btn-option " name="menu-option" value="true" checked/> 소스추가 없음
                        </label>
                        <label class="modal-option-label">
                            <input type="radio" class="modal-btn-option" name="menu-option" value="false" /> 스리라차소스 추가
                        </label>
                    </div>
                ` : ""}
            `;

        modalFooter.innerHTML = `
                <div class="modal-menu-count" >
                    <button class="decrease-qty menu-count-btn">-</button>
                    <span id="menu-qty" style="font-size: 2rem;">1</span>
                    <button class="increase-qty menu-count-btn">+</button>
                </div>
                <button id="add-menu" style="margin-top: 10px;">메뉴 추가</button>
            `;

        // 수량 선택 기능
        let qty = 1;
        const qtyDisplay = document.getElementById("menu-qty");
        document.querySelector(".decrease-qty").addEventListener("click", () => {
            if (qty > 1) {
                qty--;
                qtyDisplay.textContent = qty;
            }
        });
        document.querySelector(".increase-qty").addEventListener("click", () => {
            qty++;
            qtyDisplay.textContent = qty;
        });

        // 메뉴 추가 버튼 동작
        document.getElementById("add-menu").addEventListener("click", () => {
            //alert(`${menuName} ${qty}개가 추가되었습니다.`);
            if(menuType === "beverage"){
                beverageMenus[menuCode-1].qty1 += qty;
            } else if(menuType === "food" && menuOption === true){
                const selectedOption = document.querySelector('input[name="menu-option"]:checked').value;
                if(selectedOption === "true"){
                    foodMenus[menuCode-1].qty1 += qty;
                } else {
                    foodMenus[menuCode-1].qty2 += qty;
                }
            } else {
                foodMenus[menuCode-1].qty1 += qty;
            }

            renderChoicedMenuList();

            console.log("해당 내역 추가")
            modal.style.display = "none";
        });

        // 모달 표시
        modal.style.display = "block";
    });
});

// 모달 닫기
closeModalBtn.addEventListener("click", () => {
    modal.style.display = "none";
});

// 배경 클릭 시 모달 닫기
window.addEventListener("click", (event) => {
    if (event.target === modal) {
        modal.style.display = "none";
    }
});

const deleteOrder = document.querySelector(".delete-order");
const submitOrder = document.querySelector(".submit-order");


deleteOrder.addEventListener("click", () => {
    foodMenus.forEach(item => {
        item.qty1 = 0;
        item.qty2 = 0;
    });

    beverageMenus.forEach(item => {
        item.qty1 = 0;
        item.qty2 = 0;
    });
    renderChoicedMenuList();
})

submitOrder.addEventListener("click", () => {
    const choicedMenus = createOrderJson();

    console.log(choicedMenus);

    fetch('/customer/submit-order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // 서버에 JSON 형식으로 보내겠다고 알림
        },
        body: JSON.stringify(choicedMenus),  // JSON 객체를 문자열로 변환하여 요청 본문에 담기
        redirect: 'follow' // 리다이렉트를 따라감
    }).then(response => {
        if (response.ok) {
            return response.json();  // JSON 응답을 받음
        } else {
            throw new Error("응답 실패");
        }
    }).then(data => {
        console.log("응답 데이터:", data);

        // 리다이렉트 URL에 주문 번호를 쿼리 파라미터로 추가
        const redirectUrl = data.redirectUrl;
        const foodOrderId = data.foodOrderId;
        const beverageOrderId = data.beverageOrderId;

        // URL에 주문 번호를 쿼리 파라미터로 추가
        const fullRedirectUrl = `${redirectUrl}&foodOrderId=${foodOrderId}&beverageOrderId=${beverageOrderId}`;

        // 리다이렉트 수행
        window.location.href = fullRedirectUrl;
    }).catch(error => {
        console.error("Error during fetch:", error);
    });

});

function createOrderJson() {
    let foodOrder = foodMenus.flatMap(item => {
        let orderItems = [];

        // qty1이 1 이상이면 qty1을 전송
        if (item.qty1 >= 1) {
            orderItems.push({
                menuCode: item.menuCode,
                qty: item.qty1,
                option: false // 옵션이 없는 상태
            });
        }

        // qty2가 1 이상이면 qty2를 전송
        if (item.qty2 >= 1) {
            orderItems.push({
                menuCode: item.menuCode,
                qty: item.qty2,
                option: true // 옵션이 있는 상태
            });
        }

        // qty1이나 qty2가 1 이상인 경우만 보내므로 빈 배열이 될 수 없음
        return orderItems;
    }).filter(item => item.qty >= 1); // qty가 1 이상인 항목만 필터링

    let beverageOrder = beverageMenus.map(item => {
        // qty1이 1 이상인 경우만 전송
        if (item.qty1 >= 1) {
            return {
                menuCode: item.menuCode,
                qty: item.qty1
            };
        }
    }).filter(item => item !== undefined); // undefined 필터링

    // 'food'와 'beverage'로 나누어서 JSON을 반환
    return {
        foodOrder: foodOrder,
        beverageOrder: beverageOrder
    };
}

