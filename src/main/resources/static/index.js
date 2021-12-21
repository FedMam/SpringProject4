function submit() {
    firstName = document.getElementById('firstName').value;
    lastName = document.getElementById('lastName').value;
    phoneNumber = document.getElementById('phoneNumber').value;
    emailAddress = document.getElementById('emailAddress').value;
    grade = document.getElementById('grade').value;
    age = document.getElementById('age').value;
    country = document.getElementById('country').value;
    region = document.getElementById('region').value;
    city = document.getElementById('city').value;
    school = document.getElementById('school').value;
    if (firstName == '' || lastName == '' || phoneNumber == '' || emailAddress == '' || grade == '')
    {
        document.getElementById('error').innerHTML = 'Не заполнены обязательные поля';
        return;
    }
    const Participant = {};
    Participant.firstName = firstName;
    Participant.lastName = lastName;
    Participant.phoneNumber = phoneNumber;
    Participant.emailAddress = emailAddress;
    Participant.grade = grade;
    Participant.age = age;
    Participant.country = country;
    Participant.region = region;
    Participant.city = city;
    Participant.school = school;
    $.ajax({url: "http://localhost:8080/api/participants",
            type: 'POST',
            contentType:"application/json;charset=utf-8",
            data: JSON.stringify(Participant),
            success: function () {
                window.alert('Вы были успешно зарегистрированы');
                location.replace('./participants');
            },
            error: function (request, message, error) {
                document.getElementById('error').innerHTML = 'Ошибка: ' + message;
            }
        });
}