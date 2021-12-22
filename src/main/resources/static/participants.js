$(document).ready(function () {
    window.alert('Прошло отделение первой ступени');
    $.ajax({
        url: 'http://localhost:8080/api/participants',
        type: 'GET',
        dataType: 'json',
        success: function (participants) {
            window.alert('Прошло отделение второй ступени');
            $("#table").append("<tbody></tbody>");
            participants.forEach(function (index, participant) {
                window.alert(participant.firstName);
                let row = '<tr><td>';
                row += participant.firstName + ' ' + participant.lastName;
                row += "</td><td>";
                row += participant.grade;
                row += "</td><td>";
                if (participant.age != 0) row += participant.age;
                else row += '-';
                row += "</td><td>";
                country = participant.country;
                region = participant.region;
                city = participant.city;
                if (city != '') {
                    if (country == '')
                        row += city;
                    else {
                        if (region != '')
                            row += city + ', ' + region + ', ' + country;
                        else
                            row += city + ', ' + country;
                    }
                } else {
                    if (country != '') {
                        if (region != '') row += region + ', ' + country;
                        else row += county;
                    } else row += '-';
                }
                row += '</td><td>';
                if (participant.school == '') row += '-';
                else row += participant.school;
                row += '</td><td>';
                row += 'Телефон: ' + participant.phoneNumber + ' Email: ' + participant.emailAddress;
                row += '</td></tr>';
                $('#table tbody').append(row);
            });
        },
        error: function (request, message, error) {
            window.alert('Ошибка: ' + message)
        }
    });
  });