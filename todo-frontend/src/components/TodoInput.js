
export default function TodoInput( {value, onChange, onAdd}){
    return (
        <div>   
          <input
            type="text"
            value={value}
            onChange={(e) => onChange(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === 'Enter'){
                onAdd();
              }
            }}
            placeholder="Enter new TODO..."
          />
          <button onClick={onAdd}>ADD</button>
        </div>
    )
}



/*
<div>
          <input
            type="text"
            value={newTodoTitle}
            onChange={(e) => setNewTodoTitle(e.target.value)}
            onKeyDown={(e) => {
              if (e.key === 'Enter'){
                handleAddTodo();
              }
            }}
            placeholder="Enter new TODO..."
          />
          <button onClick={handleAddTodo}>ADD</button>
        </div>

<TodoInput value={newTodoTitle} onChange={setNewTodoTitle} onAdd={handleAddTodo}></TodoInput>
        

*/