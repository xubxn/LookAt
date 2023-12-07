<!-- Initialize Swiper -->
const progressCircle = document.querySelector(".autoplay-progress svg");
const progressContent = document.querySelector(".autoplay-progress span");
var swiper = new Swiper(".mySwiper", {
    effect: "coverflow",
    grabCursor: true,
    centeredSlides: true,
    speed: 1000,
    slidesPerView: "auto",
    coverflowEffect: {
        rotate: 20,
        stretch: 50,
        depth: 200,
        modifier: 1,
        slideShadows: true,
    },
    loop: false,
    // loopedSlides: 1,
    // pagination: {
    //     el: ".swiper-pagination",
    // },
    autoplay: {
        delay: 2000,
        disableOnInteraction: false,
    },
    on: {
        autoplayTimeLeft(s, time, progress) {
            progressCircle.style.setProperty("--progress", 1 - progress);
            // <%--progressContent.textContent = `${Math.ceil(time / 1000)}s`;--%>
        }
    }

});