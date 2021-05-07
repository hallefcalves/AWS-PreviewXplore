const listGames = () => {
    // requisição com o XMLHttpRequest
    const request = new XMLHttpRequest()
console.log("teste")
    request.open('GET', '/games')

    request.onload = function () {
        console.log(JSON.parse(this.responseText))
    }

    request.onerror = function () {
        console.log('erro ao executar a requisição')
    }

    request.send()
}

document.addEventListener("onload", listGames)