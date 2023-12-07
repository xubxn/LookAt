
var naverLogin = new naver.LoginWithNaverId({
	clientId: "RrV9r97FmFEGFKslql0F",
	callbackUrl: "http://localhost/member/navercallback.jsp",
	isPopup: true, /* 팝업을 통한 연동처리 여부 */
	loginButton: {color: "green", type: 1, height: 20}
});

naverLogin.init();/* 설정정보를 초기화하고 연동을 준비 */