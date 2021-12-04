$(function(){
    $('.btn-primary').click(function(){
        if ($('input[name=name]').val() === '') {
            alert('Не заполнено имя');
            return false;
        }
    });
});