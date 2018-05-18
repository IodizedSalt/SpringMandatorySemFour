function upvote() {

    console.log("upvote");
    var upbutton = document.querySelector("#updoot");

    upbutton.onclick = function () {
        $("#updoot").addClass("btn-up");
        $("#downdoot").removeClass("btn-down");
    }

}
function downvote() {
    console.log("downvote");
    var downbutton = document.querySelector("#downdoot");
    downbutton.onclick = function () {
        $("#updoot").removeClass("btn-up");
        $("#downdoot").addClass("btn-down")   ;
    }

}

/**
 * Created by Chris on 01-05-18.
 */
