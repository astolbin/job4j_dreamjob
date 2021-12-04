$(function(){
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8090/dreamjob/city',
        dataType: 'json'
    }).done(function (data) {
        var candidateCityId = $('.candidate-city-id').val();
        for (var city of data) {
            var selected = candidateCityId > 0 && candidateCityId == city.id ? 'selected' : '';
            $('#city-select').append(
                `<option value="${city.id}" ${selected}>${city.name}</option>`
            );
        }
    }).fail(function (err) {
        console.log(err);
    });


    $('.btn-primary').click(function(){
        if ($('input[name=name]').val() === '') {
            alert('Не заполнено имя');
            return false;
        }

        if ($('#city-select').val() === '') {
            alert('Не выбран город');
            return false;
        }
    });
});