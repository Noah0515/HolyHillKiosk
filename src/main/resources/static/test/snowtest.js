document.addEventListener('DOMContentLoaded', () => {
    const snowCount = 200; // 눈송이 개수
    const styleSheet = document.styleSheets[0]; // 첫 번째 스타일시트 가져오기

    for (let i = 0; i < snowCount; i++) {
        // 1. 눈송이 요소 생성
        const snowflake = document.createElement('div');
        snowflake.classList.add('snow');
        document.body.appendChild(snowflake);

        // 2. 랜덤 변수 생성
        const startX = Math.random() * 100; // 시작 위치 (뷰포트 너비의 백분율)
        const endX = startX + (Math.random() * 20 - 10); // 끝 위치
        const endXYoyo = startX + (endX - startX) / 2; // 요요 위치
        const yoyoY = Math.random() * 50 + 25; // 요요 지점
        const scale = Math.random() * 0.5 + 0.5; // 크기
        const duration = Math.random() * 20 + 10; // 지속 시간
        const delay = Math.random() * -10; // 지연 시간

        // 3. 고유 애니메이션 이름 생성
        const animationName = `fall-${i}`;
        const keyframes = `
      @keyframes ${animationName} {
        0% {
          transform: translate(${startX}vw, -10px) scale(${scale});
        }
        ${yoyoY}% {
          transform: translate(${endX}vw, ${yoyoY}vh) scale(${scale});
        }
        100% {
          transform: translate(${endXYoyo}vw, 100vh) scale(${scale});
        }
      }
    `;
        styleSheet.insertRule(keyframes, styleSheet.cssRules.length);

        // 4. 애니메이션 적용
        snowflake.style.animation = `${animationName} ${duration}s ${delay}s linear infinite`;
    }
});