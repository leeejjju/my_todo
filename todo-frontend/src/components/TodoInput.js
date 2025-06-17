import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

export default function TodoInput( {value, onChange, onAdd}){
    return (
      <div 
      style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between',
        padding: '8px 16px',
        gap: '10px'
      }}
      // style={{ display: 'flex', alignItems: 'center', gap: '10px' }}
      >
        
        <TextField 
          id="outlined-basic" 
          label="Enter new TODO" 
          variant="outlined"
          defaultChecked size="small" 
          value={value}
          onChange={(e) => onChange(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === 'Enter'){
              onAdd();
            }
          }}
          style={{ width: '80%' }}
        />
        
        <Button 
          color='primary'
          variant="contained" 
          onClick={onAdd}
          defaultChecked size="big" 
          style={{ width: '10%', padding: "5px"}}
        >
            ADD
        </Button>
      </div>
    )
}
