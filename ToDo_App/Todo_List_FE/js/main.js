
const URL_API = "http://localhost:8080/api/todos";

const todoListEl = document.querySelector(".todo-list");


// Luu lai cong viec
let todos = [];


// ===== Danh sach API 

// 1. Lay tat ca CV 
const getTodoAPI = () => {
    return axios.get(URL_API); // tra ve promise
}

// 2. Xoa CV 
const deleteTodoAPI = (id) => {
    return axios.delete(`${URL_API}/${id}`); // tra ve promise
}



// ===== Ham xu ly

// 1. Lay danh sach tat ca cv
const getTodo = async () => {
    try {
        let res = await getTodoAPI();
        console.log(res);

        todos = res.data; // Luu data vao todos de render tren FE
        renderTodo(res.data);

    } catch (error) {
        console.log(error);

    }
}

// 2. Xoa CV 

const deleteTodo = async (id) =>{
    try{
        let isConfirm = confirm("Ban co muon xoa khong ?")

        if(isConfirm){
            await deleteTodoAPI(id); // Xoa tren server

            // Xoa tren mang todos (mang ban dau)
            todos = todos.filter(t => t.id != id);

            renderTodo(todos); // render lai sau khi xoa 
        }

    }catch(error){
        console.log(error);
    }
}



// ---------------------------

const renderTodo = arr => {

    todoListEl.innerHTML = "";

    if (arr.length == 0) {
        todoListEl.innerHTML = "Emty Todo List";
        return;
    }

    let html = "";

    arr.forEach(t => {
        html += `
        <div class="todo-item ${t.status ? "active-todo" : ""}">
            <div class="todo-item-title">
                <input type="checkbox" ${t.status ? "checked": ""}/> 
                <p>${t.name}</p>
            </div>
             <div class="option">
                <button class="btn btn-update">
                    <img src="./img/pencil.svg" alt="icon" />
                </button>
                <button class="btn btn-delete" onclick = "deleteTodo(${t.id})">
                    <img src="./img/remove.svg" alt="icon" />
                </button>
            </div>
        </div>

        `
    });

    todoListEl.innerHTML = html;





}

getTodo();