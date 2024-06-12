function getSideTime() {
	var departLoc = $('#info-departLoc').text();
	var arrivalLoc = $('#info-arrivalLoc').text();

	function updateTimeRange() {
		console.log('사이드바 출발 시간대');
		// 출발
		var departTimeLeftVal = $('#depart-left-timeval').text()/*.split(':')[0]*/;
		var departTimeRightVal = $('#depart-right-timeval').text()/*.split(':')[0]*/;
		// 도착
		var arrivalTimeLeftVal = $('#arrival-left-timeval').text()/*.split(':')[0]*/;
		var arrivalTimeRightVal = $('#arrival-right-timeval').text()/*.split(':')[0]*/;

		console.log('출발지 : ', departLoc);
		console.log('도착지 : ', arrivalLoc);
		console.log('LEFT 출발 시간대 : ', departTimeLeftVal);
		console.log('RIGHT 출발 시간대 : ', departTimeRightVal);
		console.log('LEFT 도착 시간대 : ', arrivalTimeLeftVal);
		console.log('RIGHT 도착 시간대 : ', arrivalTimeRightVal);

		$.ajax({
			url: "/shushoong/airline/list_side_time/ajax",
			method: "get",
			data: {
				departLoc: departLoc,
				arrivalLoc: arrivalLoc,
				departTimeLeft: departTimeLeftVal,
				deaprtTimeRight: departTimeRightVal,
				arrivalTimeLeft: arrivalTimeLeftVal,
				arrivalTimeRight: arrivalTimeRightVal
			},
			dataType: 'json',
			success: function(response) {
				console.log('Ajax Success', response);
				updateAirlineList(response);
			},
			error: function(xhr, status, error) {
				console.log('AJAX 실패:', error);
			}
		});
	}

	// 양방향 레인지 바 이벤트 핸들러 설정
	$('#depart-input-left').on('click', updateTimeRange);
	$('#depart-input-right').on('click', updateTimeRange);
	$('#arr-input-left').on('click', updateTimeRange);
	$('#arr-input-right').on('click', updateTimeRange);
}

function updateAirlineList(data) {
	// 항공 목록을 업데이트
	$('.airline-info-container').empty(); // 기존 목록 초기화

	data.forEach(function(air) {
		var airlineInfo = `
            <div class="airline-info">
                <div hidden="">
                    <span class="select-info-departLoc" name="departLoc">${air.departLoc}</span>
                    <span class="select-info-arrivalLoc" name="arrivalLoc">${air.arrivalLoc}</span>
                    <span class="select-info-airlineCode" name="airlineCode">${air.airlineCode}</span>
                </div>
                <div class="seat-count">
                    <div class="airline-flex">
                        <div class="airline-logo">
                            <div class="span-seat-div">
                                <span class="span-seat">잔여 ${air.seatCount}석</span>
                            </div>
                            <img src="${air.airlineImg}" alt="">
                        </div>
                        <div class="airline-name">
                            <span>${air.airlineName}</span>
                        </div>
                        <div class="airinfo depart-info">
                            <div class="info date">${air.departDate}</div>
                            <div class="info time">${air.departTime}</div>
                            <div class="info loc depart">${air.departLoc}</div>
                        </div>
                        <div class="arrow">
                            <div class="via-count">
                                <span>${air.viaCount}</span>
                            </div>
                            <img src="/shushoong/images/airline_via.png" alt="">
                            <div class="flytime">${air.flightTime}</div>
                            <div hidden>${air.flightNo}</div>
                        </div>
                        <div class="airinfo arr-info">
                            <div class="info date">${air.arrivalDate}</div>
                            <div class="info time">${air.arrivalTime}</div>
                            <div class="info loc arrival">${air.arrivalLoc}</div>
                        </div>
                        <div class="nop">
                            <div class="sero">
                                <img src="/shushoong/images/airline_line.png" alt="">
                            </div>
                            <div class="ticket-adult">성인 1</div>
                            <div class="airticket">
                                <div class="ticket-price">
                                    <div>${air.ticketPrice}</div>
                                </div>
                                <div class="ticket-btn">
                                    <button class="ticketinfo-btn" type="button">
                                        <img class="price-img-btn" src="/shushoong/images/skyblue_right.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
		$('.airline-info-container').append(airlineInfo);
	});
}