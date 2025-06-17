import DeleteIcon from '@mui/icons-material/Delete';
import IconButton from '@mui/material/IconButton';
import Checkbox from '@mui/material/Checkbox';
import ListItem from '@mui/material/ListItem';
import Typography from '@mui/material/Typography';

// components/TodoItem.js
export default function TodoItem({todo, onToggle, onDelete }) {
  return (
    <ListItem
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        padding: '8px 12px',
        borderBottom: '1px solid #ddd',
      }}
    >
      <div style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
        <Typography
          variant="body1"
          style={{
            // textDecoration: todo.completed ? 'line-through' : 'none',
            // color: todo.completed ? '#aaa' : '#333',
          }}
        >
          {todo.completed ? '✅' : '❌'} {todo.title}
        </Typography>

      </div>    
      <div>
        <Checkbox 
          color='primary'
          checked={todo.completed} 
          onChange={() => onToggle(todo.id)} />
        <IconButton
          color="default"
          size="small"
          onClick={() => onDelete(todo.id)}
          aria-label="delete TODO" 
        >
          <DeleteIcon />
        </IconButton>
      </div>
    </ListItem>
  );
}
