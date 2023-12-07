(function () {
    calendarMaker($("#calendarForm"), new Date());
})();

var reserveInfo = ""; // 예약 정보 변수
var nowDate = new Date();

function calendarMaker(target, date) {
    if (date == null || date == undefined) {
        date = new Date();
    }
    nowDate = date;
    if ($(target).length > 0) {
        var year = nowDate.getFullYear();
        var month = nowDate.getMonth() + 1;
        $(target).empty().append(assembly(year, month));
    } else {
        console.error("custom_calendar Target is empty!!!");
        return;
    }

    var thisMonth = new Date(nowDate.getFullYear(), nowDate.getMonth(), 1);
    var thisLastDay = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, 0);

    var tag = "<tr>";
    var cnt = 0;
    //빈 공백 만들어주기
    for (i = 0; i < thisMonth.getDay(); i++) {
        tag += "<td></td>";
        cnt++;
    }

    //날짜 채우기
    for (i = 1; i <= thisLastDay.getDate(); i++) {
        if (cnt % 7 == 0) {
            tag += "<tr>";
        }

        tag += "<td>" + i + "<span class='reserve_p'></span></td>";
        cnt++;
        if (cnt % 7 == 0) {
            tag += "</tr>";
        }
    }
    $(target).find("#custom_set_date").append(tag);
    calMoveEvtFn();

    function assembly(year, month) {
        var calendar_html_code =
            "<table class='custom_calendar_table'>" +
            "<colgroup>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "<col style='width:81px'/>" +
            "</colgroup>" +
            "<thead class='cal_date'>" +
            "<th><button type='button' class='prev'><</button></th>" +
            "<th colspan='5'><p><span>" + year + "</span>년 <span>" + month + "</span>월</p></th>" +
            "<th><button type='button' class='next'>></button></th>" +
            "</thead>" +
            "<thead  class='cal_week'>" +
            "<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>" +
            "</thead>" +
            "<tbody id='custom_set_date'>" +
            "</tbody>" +
            "</table>";
        return calendar_html_code;
    }

    function calMoveEvtFn() {
        //전달 클릭
        $(".custom_calendar_table").on("click", ".prev", function () {
            nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() - 1, nowDate.getDate());
            calendarMaker($(target), nowDate);
        });
        //다음달 클릭
        $(".custom_calendar_table").on("click", ".next", function () {
            nowDate = new Date(nowDate.getFullYear(), nowDate.getMonth() + 1, nowDate.getDate());
            calendarMaker($(target), nowDate);
        });
        //일자 선택 클릭
        $(".custom_calendar_table").on("click", "td", function() {
        	if ($(this).hasClass("select_day")) {
                // 이미 선택한 날짜를 클릭한 경우
                $(this).removeClass("select_day");
                $(this).find(".reserve_p").text(""); // 예약 날짜 초기화
                reserveInfo = ""; // 예약 정보도 초기화
            } else {
                // 선택한 날짜로 reserveInfo 값 설정
                var day = $(this).text(); // 일자
                var month = nowDate.getMonth() + 1; // 월 (0부터 시작하므로 +1)
                var year = nowDate.getFullYear(); // 년

                reserveInfo = year + "-" + month + "-" + day; // 연월일 형식으로 저장
                // 다른 날짜를 선택한 경우
                $(".custom_calendar_table .select_day").removeClass("select_day");
                $(this).addClass("select_day");
                $(this).find(".reserve_p").text(""); // 선택한 날짜를 표시
            }
        });
    }
}

$(document).ready(function() {
	  var selectedBtns = []; // 선택한 권종 버튼들을 저장할 배열
	  // 총 결제금액 초기화
	  updateTotalPrice();

	  // 권종 선택 버튼 클릭 이벤트
	  $("#adult, #teenager, #childern").click(function() {
	    var isSelected = $(this).hasClass('active'); // 해당 버튼이 이미 선택되었는지 확인

	    // 선택한 가격을 수량 선택 항목에 표시
	    var price = parseInt($(this).find(".title2_price").text().replace(/[^0-9]/g, ""));
	    $(this).closest(".selectBox").find(".price").text(price.toLocaleString() + "원");

	    // 선택한 버튼이 이미 선택된 상태이면 선택 해제
	    if (isSelected) {
	      $(this).removeClass('active');
	      selectedBtns = selectedBtns.filter(function(btn) {
	        return btn.attr('id') !== $(this).attr('id');
	      });
	    } else { // 선택되지 않은 상태이면 선택 추가
	      $(this).addClass('active');
	      selectedBtns.push($(this));
	    }

	    showNumSelect();

	    // 선택한 권종 버튼에 active 클래스 추가
	    function showNumSelect() {
	      // 수량선택 영역 보이기
	      $('.num_select').show();

	      // 모든 권종 선택 영역 숨기기
	      $('.select_item').hide();

	      // 선택한 권종에 해당하는 수량 선택 영역 보이기
	      for (var i = 0; i < selectedBtns.length; i++) {
	        $('#' + selectedBtns[i].attr('name')).show();
	      }
	    }

	  });

	  $('.remove_ticket1').click(function() {
	    var item = $(this).closest('.select_item');
	    var btnId = item.attr('id');

	    // 선택한 권종 버튼에서 해당 아이템 제거
	    selectedBtns = selectedBtns.filter(function(btn) {
	      return btn.attr('id') !== btnId;
	    });

	    // 선택한 권종에 해당하는 수량 선택 영역 숨기기
	    item.hide();

	    // 선택한 권종 버튼에 active 클래스 제거
	    $('#' + btnId).removeClass('active');

	    // 선택한 권종 버튼에 대한 name 출력
	    $('#' + btnId).closest(".selectBox").find(".select_name").text("");
	    updateTotalPrice(); // 선택한 권종이 변경되면 총 결제금액 업데이트
	  });

	  // 수량 선택 버튼 클릭 이벤트
	  $('.add_ticket').click(function() {
	    var quantity = parseInt($(this).siblings('.select_quanatity').text(), 10);
	    quantity++;
	    $(this).siblings('.select_quanatity').text(quantity);
	    $(this).siblings('.select_quanatity').attr('value', quantity); // value 값 설정
	    updateTotalPrice(); // 수량 변경으로 인해 총 결제금액 업데이트
	  });

	  $('.remove_ticket2').click(function() {
	    var quantity = parseInt($(this).siblings('.select_quanatity').text(), 10);
	    if (quantity > 0) {
	      quantity--;
	      $(this).siblings('.select_quanatity').text(quantity);
	      $(this).siblings('.select_quanatity').attr('value', quantity); // value 값 설정
	      updateTotalPrice(); // 수량 변경으로 인해 총 결제금액 업데이트
	    }
	  });

	  // 총 결제금액 초기화
	  updateTotalPrice();

	  function updateTotalPrice() {
	    var total = 0;

	    // 선택한 모든 권종의 가격 합산
	    $('.select_item').each(function() {
	      var quantity = parseInt($(this).find('.select_quanatity').text(), 10);
	      var price = parseInt($(this).find('.item_price').val(), 10);
	      if (!isNaN(quantity) && !isNaN(price)) {
	        total += quantity * price;
	      }
	    });
	    // 총 결제금액 표시
	    $('.total_price').text(total.toLocaleString() + '원');
	    
	  }
	});


$(document).ready(function() {
	$('#btn_5').on('click', function() {
		var memberid = $("#memid").val();
		var title = $("#restitle").val();
		var going_date = reserveInfo;
		var exhibition_no = $("#exhibition_no").val();
		var price_no = $("#price_no").val();
		var total_adult = $("#quan").text();
		var total_student = $("#quan2").text();
		var total_baby = $("#quan3").text();
		var total_price = 0;

		    // 모든 권종의 가격을 합산하여 total_price에 저장
		    $('.select_item').each(function() {
		      var quantity = parseInt($(this).find('.select_quanatity').text(), 10);
		      var price = parseInt($(this).find('.item_price').val(), 10);
		      if (!isNaN(quantity) && !isNaN(price)) {
		        total_price += quantity * price;
		      }
		    });
		    
		    
		  $.ajax({
			  url: "/reserve.do",
			  type: "POST",
			  data: {
			    member_id: memberid,
			    exhibition_no: exhibition_no,
			    price_no: price_no,
			    going_date: going_date,
			    total_adult : total_adult,
			    total_student : total_student,
			    total_baby : total_baby,
			    total_price : total_price
			  },
			  success: function(response) {
				  alert("결제를 진행합니다.");
				  console.log(exhibition_no, memberid, title, price_no, going_date, total_adult, total_student, total_baby, total_price);
			  },
			  error: function(jqXHR, textStatus, errorThrown) {
			    // 요청이 실패한 경우 실행할 코드를 작성하세요.
				  alert("실패했습니다.");
			  }
		});
		    requestPay(total_price); // total_price를 인자로 전달하여 requestPay 함수 실행
		    function requestPay(total_price) {
		    	  // requestPay 함수를 수정하여 결제 API에 total_price 값을 전달하고, 실행할 코드를 작성하세요.
		    	  var IMP = window.IMP;
		    	  var merchant_uid = '123456-' + new Date().getTime(); // 예: '57008833-1627640125000'

		    	  IMP.init("imp76217741");

		    	  IMP.request_pay(
		    	    {
		    	      pg: "kcp.TC0ONETIME",
		    	      pay_method: "card",
		    	      merchant_uid: merchant_uid,
		    	      name: title,
		    	      amount: total_price, // 전달받은 total_price 값을 결제 API에 사용
		    	      buyer_email: "Iamport@chai.finance",
		    	      buyer_name: "포트원 기술지원팀",
		    	      buyer_tel: "010-1234-5678",
		    	      buyer_addr: "서울특별시 강남구 삼성동",
		    	      buyer_postcode: "123-456",
		    	    },
		    	    function (rsp) {
			              if (rsp.success) {
			              	// 결제 성공 시: 결제 승인 또는 가상계좌 발급에 성공한 경우
			                    // jQuery로 HTTP 요청
			                    ajax({
			                      url: "/IamportController",
			                      method: "POST",
			                    }).done(function (data) {
			                  	// 위의 rsp.paid_amount 와 data.response.amount를 비교한후 로직 실행 (iamport 서버검증)
			                  	  if(rsp.paid_amount == data.response.amount){
			        		        	succeedPay(rsp.imp_uid, rsp.merchant_uid);
			        	        	} else {
			        	        		alert("결제 검증 실패");
			        	        	}
			                    })
			                } else {
			              	  var msg = '결제에 실패하였습니다.';
			                    msg += '에러내용 : ' + rsp.error_msg;
			                    alert(msg);
			                }
			          }
		    	   );
		      }
	});
});
