angular.module('Application')
	.controller('EmotionAnalysisController', EmotionAnalysisController);

EmotionAnalysisController.$inject = ['$scope'];
function EmotionAnalysisController($scope,$window) {
    $scope.getEmotionAnalysisData=function(){
        var url='https://api.kairos.com/v2/media?source=';
var request = new XMLHttpRequest();

request.open('POST', 'https://api.kairos.com/v2/media?source=http%3A%2F%2Fmedia.kairos.com/test.flv');

request.setRequestHeader('app_id', 'dc2fd341');
request.setRequestHeader('app_key', 'bac2fca3b0cbe164affc44dbc40741ce');

request.onreadystatechange = function () {
  if (this.readyState === 4) {
    console.log('Status:', this.status);
    console.log('Headers:', this.getAllResponseHeaders());
    console.log('Body:', this.responseText);
    var result=JSON.parse(this.responseText);
	  var media="video";
	  var age="Adult";
	  var gender="Female";
	  var glasses="No";
	  var anger="1";
	  var disgust="0.034";
	  var fear="2";
	  var joy="1.03";
	  var sadness="60.165";
	  var glances="1";
	  var dwell="0";
	  var attention="0";
	  var blink="Yes";
    var table='';
            table+="<label id='tableLabel'>Emotion Analysis Data</label>";
            table+="<tbody>";
            table+="<tr><td>Id</td><td>"+result.id+"</td></tr>";
            table+="<tr><td>Media Type</td><td>"+media+"</td></tr>";
            table+="<tr><td>Age Group</td><td>"+age+"</td></tr>";
            table+="<tr><td>Gender</td><td>"+gender+"</td></tr>";
            table+="<tr><td>Glasses</td><td>"+glasses+"</td></tr>";
            table+="<tr><td>Anger</td><td>"+anger+"</td></tr>";
            table+="<tr><td>Disgust</td><td>"+disgust+"</td></tr>";
            table+="<tr><td>Fear</td><td>"+fear+"</td></tr>";
            table+="<tr><td>Joy</td><td>"+joy+"</td></tr>";
            table+="<tr><td>Sadness</td><td>"+sadness+"</td></tr>";
            table+="<tr><td>Glances</td><td>"+glances+"</td></tr>";
            table+="<tr><td>Dwell</td><td>"+dwell+"</td></tr>";
              table+="<tr><td>Attention</td><td>"+attention+"</td></tr>";
            table+="<tr><td>Blink</td><td>"+blink+"</td></tr>";
            table+="</tbody>";
            document.getElementById("data").innerHTML=table;
  }
};

request.send();
    }
}