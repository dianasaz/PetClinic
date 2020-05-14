function checkLogin() {
    var input = document.getElementById("login");
    if(!/^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}

function checkPassword() {
    var input = document.getElementById("password");
    if(!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]*)(?!.*\s).{8,33}$/.test(input.value)){
        input.style.border = "2px solid #ed1d12";
    }else{
        input.style.border = "none";
    }
}


function checkName() {

    var inputName = document.getElementById("name");
    if (!/^[A-ZА-Я][a-zа-я]{1,16}\s[A-ZА-Я][a-zа-я]{1,16}$/.test(inputName.value)) {
        inputName.style.border = "2px solid #ed1d12";
    } else {
        inputName.style.border = "none";
    }
}

function checkEmail() {

    var inputEmail = document.getElementById("email");
    if (!/^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$/.test(inputEmail.value)) {
        inputEmail.style.border = "2px solid #ed1d12";
    } else {
        inputEmail.style.border = "none";
    }
}

function checkPhone() {

    var inputPhone = document.getElementById("phoneNumber");
    if (!/[0-9]{9}/.test(inputPhone.value)) {
        inputPhone.style.border = "2px solid #ed1d12";
    } else {
        inputPhone.style.border = "none";
    }
}

function checkAddress() {

    var inputAddress = document.getElementById("Address");
    if (!/^([A-Za-zА-Яа-я]{1,10}\.?\s?)?[a-zA-ZА-Яа-я]{1,20}\s\d{1,3}(\-\,\d{1})?([\.\s\,a-zа-я]{1,10}[\d]{1,5})?/.test(inputAddress.value)) {
        inputAddress.style.border = "2px solid #ed1d12";
    } else {
        inputAddress.style.border = "none";
    }
}

function checkPetName() {

    var inputPetName = document.getElementById("name");
    if (!/^[A-ZА-Я][a-zа-я]{1,16}$/.test(inputPetName.value)) {
        inputPetName.style.border = "2px solid #ed1d12";
    } else {
        inputPetName.style.border = "none";
    }
}

function checkServiceName() {

    var input = document.getElementById("name");
    if (!/^[A-ZА-Я][a-zа-я]+\s?[A-ZА-Я]?[a-zа-я]+\s?[A-ZА-Я]?[a-zа-я]+$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}

function checkServicePrice() {

    var inputPrice = document.getElementById("price");
    if (!/^[1-9][0-9]{1,6}$/.test(inputPrice.value)) {
        inputPrice.style.border = "2px solid #ed1d12";
    } else {
        inputPrice.style.border = "none";
    }
}

function checkDoctor() {

    var input = document.getElementById("name");
    if (!/^[A-ZА-Я][a-zа-я]+\s[A-ZА-Я][a-zа-я]+(\s[A-ZА-Я][a-zа-я]+)?$/.test(input.value)) {
        input.style.border = "2px solid #ed1d12";
    } else {
        input.style.border = "none";
    }
}

function checkSearch() {
    var input = document.getElementById("search");
    var button = document.getElementById("button_search");
    if (!/^.+$/.test(input.value)) {
        button.disabled = true;
    } else {
        button.disabled = false;
    }


}

