function createDiv() {

    console.log("create-div")

    const a='some text'
    fetch("createDiv")
        .then(response => response.json())
        .then(data => {
            console.log(data)
        })
}

export {createDiv};