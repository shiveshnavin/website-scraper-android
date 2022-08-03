console.log('>>>>>>>>>>>>>>>>> Injection completed !!')

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

//// LOGIN PAGE
function login(user,pass) {
    console.log("Logging in");
    $("#username").val(user)
    $("#password").val(pass)
    console.log("Username set " + $("#username").val());
    console.log("Password set " + $("#password").val());

    $("#organic-div > form > div.login__form_action_container > button").click()
    return "logged in"
}


function search(term) {
    console.log("$(`#search-input`) " + $(`#search-input`).length)
    $(`#search-input`).click()

    $(`#search-input`).val(term)
    $(`#search-input`).value = term

    return "searching"
}

function scrapProfiles() {
    var ull = $('#app-container > section.search-container > ul');
    var ul = $('#app-container > section.search-container > ul li');
    console.log("UL " + ul.length)

    ull.on('DOMSubtreeModified', function (event) {
        // console.log("UL UPDATED " + ul.length)
        readUL(ul, ull);

    });

    readUL(ul, ull);


}


async function readUL(ul, ull) {
    ul = $('#app-container > section.search-container > ul li');
    ul.each(function (idx, li) {
        var elem = $(li)

        var containerDiv = elem.first();
        var aUrl = containerDiv.find("a")
        var img = aUrl.find("img")

        var detailsDivCont = containerDiv.find("div")
        var detailsDiv = detailsDivCont.find("div")
        var aDetailsDiv = detailsDiv.find("a")

        // console.log('a = ' + JSON.stringify(a.html()))
        // console.log('IMG LEN ' + img)

        var name = aDetailsDiv.find('h3 > span:nth-child(1)').text();
        var role = aDetailsDiv.find('div > span').text();
        var location = aDetailsDiv.find('dd > span').text();

        JSInterface.addProfileUrl(aUrl.attr('href'), name, role, location, img.attr('src'))


    });

    await sleep(JSInterface.getScrollDelay());
    $(document).ready(function () {
        if (!JSInterface.isLoadedEnoughProfiles()) {
            $(document).scrollTop($(document).height());
        }
        else {
            
        }
    });
}