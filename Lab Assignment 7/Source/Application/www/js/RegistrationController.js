angular
    .module('Application')
    .controller('RegistartionController', RegistartionController);

RegistartionController.$inject = ['UserService','$scope'];
function RegistartionController(UserService, $scope,$window,$filter,$q) {
    $scope.IsRegLogedIn= false;
    $scope.RegistrationMessageSuccess='';
    $scope.RegistrationMessageFailure='';
    $scope.RegSubmitted= false;
    $scope.IsRegFormValid=false;

    $scope.$watch('registrationForm.$valid',function(newVal){
        $scope.IsRegFormValid=newVal;
    });

    $scope.RegistrationData={
        Username:'',
        Password:''
    };
    $scope.reset = function() {
        $scope.RegistrationData = '';
        // Todo: Reset field to pristine state, its initial state!
    };
    $scope.Register=function(){
        $scope.RegSubmitted=true;
        $scope.IsRegLogedIn=true;
        UserService.CreateUser($scope.RegistrationData)
            .then(function (response) {
                if (response.success) {
                    $scope.RegistrationMessageFailure='';
                    $scope.RegistrationMessageSuccess="Registration Successful";
                }
                else{
                    $scope.RegistrationMessageSuccess='';
                    $scope.RegistrationMessageFailure="Username "+$scope.RegistrationData.Username+" already exists";
                }
            });
    }
}