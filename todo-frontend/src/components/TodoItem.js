// components/TodoItem.js
export default function TodoItem({todo, onToggle, onDelete }) {
  return (
    <li>
      <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
        <span>{todo.completed ? '✅' : '❌'} {todo.title}</span>
        <input
          type="checkbox"
          checked={todo.completed}
          onChange={() => onToggle(todo.id)}
        />
        <button
          onClick={() => onDelete(todo.id)}
          style={{
            background: 'transparent',
            border: 'none',
            fontSize: '0.8rem',
            cursor: 'pointer',
          }}
        >
          🗑️
        </button>
      </div>
    </li>
  );
}
