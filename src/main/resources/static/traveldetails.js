angular.module('travelApp',[]);
travelApp.controller('travelController',function($scope,$http){
	$http.get('http://localhost:9000/travel/calculatefare').success(function(response)){
		$scope.travelresponse=response.faredetails;
		
	});
});