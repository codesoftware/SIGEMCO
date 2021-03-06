//Funcion para validar solo la entrada de numeros
function validaNumeros(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8) {
        return true;
    }
    patron = /[0-9]/;
    tecla_final = String.fromCharCode(tecla);
    return patron.test(tecla_final);
}
//Funcion mascara de moneda
function mascaraMoneda(input) {
    var num = input.value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    }
}

function mascaraMonedaConValor(valor) {
    var num = valor.replace(/\,/g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\,?)(\d{3})/g, '$1,');
        num = num.split('').reverse().join('').replace(/^[\,]/, '');
        return num;
    }
}
/**
 * Funcion la cual no permite ingresar numeros a la aplicacion
 * @param {type} e
 * @returns {Boolean}
 */
function soloNumeros(e) {
    var evt = (evt) ? evt : window.event
    var charCode = (evt.which) ? evt.which : evt.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 44) {
        event.returnValue = false;
    } else {
        return true
    }
}


//Funcion mejorada  mascara moneda

function mascaraMonedaNew(o, f) {
    v_obj = o;
    v_fun = f;
    setTimeout("ejecutamascara()", 1);
}
function ejecutamascara() {
    v_obj.value = v_fun(v_obj.value);
}
function expresionRegular(v) {
    v = v.replace(/([^0-9\.]+)/g, '');
    v = v.replace(/^[\.]/, '');
    v = v.replace(/[\.][\.]/g, '');
    v = v.replace(/\.(\d)(\d)(\d)/g, '.$1$2');
    v = v.replace(/\.(\d{1,2})\./g, '.$1');
    v = v.toString().split('').reverse().join('').replace(/(\d{3})/g, '$1,');
    v = v.split('').reverse().join('').replace(/^[\,]/, '');
    return v;
}

function validaCaracteres(valor, cantidad){
    var caracteres = valor.length;
    cantidad = parseInt(cantidad);
    if(caracteres<=cantidad){
        return true;
    }
    return false;
    
}