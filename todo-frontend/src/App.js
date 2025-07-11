// useEffect와 useState는 리액트에서 제공하는 특별한 Hook. 
// useEffect는 컴포넌트의 특정 시점에 동작을 추가하는 도구. 보통 랜더링시 한번 실행되게 하는 데 사용됨 (Main함수같은건가??)
// userState: 변수처럼 값을 저장하고 바꿀 수 있게 해주는 친구 
import React, { useCallback, useEffect, useState } from "react";
import TodoItem from './components/TodoItem.js';
import TodoInput from './components/TodoInput.js';
import AssignmentTurnedInIcon from '@mui/icons-material/AssignmentTurnedIn';
import List from '@mui/material/List';
import Paper from '@mui/material/Paper';

// 자바스크립트 함수. 리액트에서는 컴포넌트로 사용됨. 컴포넌트: 어떤 UI를 보여줄지 정의한 함수 
function App() {

    // todos는 상태 변수? 할 일 목록을 배열로 저장한대... 
    // setTodos는 todos를 바꾸는 함수 
    // 아래 문법은 todos라는 변수와 setTodos라는 변수를 변경하는 함수(setter)를 함께 선언하는 것 
    const [todos, setTodos] = useState([]);
    const TODO_API_URL_READ = `${process.env.REACT_APP_API_BASE_URL_READ}/api/todos`;
    const TODO_API_URL_WRITE = `${process.env.REACT_APP_API_BASE_URL_WRITE}/api/todos`;

    // 서버에서 데이터를 불러와서 todos 변수에 저장하는 함수 
    // async는 비동기적으로 작업을 수행함: 요청 후 기다리지 않고 다음 코드로 넘어갈 수 있음 
    const fetchTodos = useCallback(async () => {
      // await: 완료될 때 까지 기다림 
      const res = await fetch(TODO_API_URL_READ); //fetch: 서버에서 데이터를 받아옴 
      const data = await res.json(); //서버에서 받아온 데이터(res)를 json 형식으로 변환 
      setTodos(data); // 위에서 todos랑 setTodos 묶은 것 처럼... setTodos 통해 todos를 조작 -> 받아온 데이터 넣기
    }, [TODO_API_URL_READ]);
    //화살표 함수 + 비동기 문법이 결합된 형태의 함수 선언 문법임. 

  //userEffect: React의 라이프사이클 훅. 시작시 한 번 호출되는 친구. 
  useEffect(
    () => { fetchTodos();} // fetchTodos라는 함수를 실행 
    , [fetchTodos] // 의존성 배열: 비어있으면 한 번만 실행 
  ); 

  //newTodoTitle이라는 변수를 setNewTodoTitle이라는 setter와 선언, 초기화인가?
  //왜 이딴식으로 쓰냐면 값이 바뀌면 UI를 다시 그려주기 위해서... 값이 바뀌는 상황을 단순히 변수에 값 대입하는게 아니라 setter를 거치게 해서 UI 새로고침의 트리거로도 사용하는 느낌스?? 
  const [newTodoTitle, setNewTodoTitle] = useState("");
  // 비동기적인 함수 선언: 안기다리고 다음으로 넘어가는... 
  const handleAddTodo = async () => {

    //없으면(null) 스킵? 
    if (!newTodoTitle.trim()) return;


    // 서버에 POST API를 보낸건가봐 
    const res = await fetch(
      TODO_API_URL_WRITE, 
      {
        method: "POST",
        headers: { "Content-Type": "application/json",},
        body: JSON.stringify({ title: newTodoTitle }),
    });

    const newTodo = await res.json();
    setTodos((prevTodo)=>[...prevTodo, newTodo]); // 임시로 새 데이터 추가해주기...! (뿌슝빠슝뿌슝)
    setNewTodoTitle(""); // 입력칸을 빈 문자열로 초기화 
    // fetchTodos(); // 새로고침 -> 임시로 추가한 값이 나중에 동기화 될 것이라는 "신뢰"를 ㄱ ㅏ지고 생략... 

  };

  const handleToggle = async (id) => {
    try {
      // 변수 ${id}가 포함되어 있을 때는 큰따옴표(") 말고 백틱?(`)을 써야 함!! 
      await fetch(`${TODO_API_URL_WRITE}/${id}/toggle`, {
        method: "PATCH",
      });
      fetchTodos(); // 다시 목록 불러오기
    } catch (err) {
      console.error("Error toggling todo:", err);
    }
  };

  const deleteTodo = async (id) => {
    await fetch(`${TODO_API_URL_WRITE}/${id}`, {
      method: "DELETE",
    });
    
    //Optimistic UI!! 일단 자체적으로 삭제시켜서 보여주기! 
    setTodos((prevTodos) => prevTodos.filter((todo) => todo.id !== id));
    // fetchTodos(); // 삭제 후 다시 목록 갱신 -> 되겠지 머~ 
  };


  //이게 화면에 띄워질 부분인 듯?? HTML문법 아녀 이거 -> HTML처럼 생긴 JSX문법. 
  return (
    <div 
    style={{ 
      padding: "2rem",
      width: "50%",
      minWidth: '300px',
      maxWidth: '500px',
      alignItems: "center",
      margin: '0 auto'
    }}>

      <h1>
        <AssignmentTurnedInIcon fontSize="big" color="action"/>
        MY TODO LIST
        <img 
          src="/images/general.PNG" 
          alt="장군이" 
          style={{ 
            width: "64px",
            height: "60px",
            objectFit: 'contain',
            position: 'relative',
            top: '16px',
            left: '8px'
          }} />
       
      </h1>

      <Paper elevation={3} style={{ padding: '16px', marginTop: '20px' }}>
        <List>
          {todos.map((todo) => (
              <li key={todo.id}>
                <TodoItem todo={todo} onToggle={handleToggle} onDelete={deleteTodo}></TodoItem>
              </li>
            )
          )}
        </List>
        <TodoInput value={newTodoTitle} onChange={setNewTodoTitle} onAdd={handleAddTodo}></TodoInput>
      </Paper>

      <div  style={{ padding: "1rem" }}></div>
    </div>
  );
  // <ui> 밑에 무슨 컴언어마냥... todos.map()이거가 원소 하나하나의 할 일을 화면에 <li>로 보여준대..
  // key-{todo.id} 이게 변수선언같은??
  // 삼항연산자 사용가능 ㅇㅇ 
  
}

// App이라는 위에서 정의한 함수(UI 컴포넌트)를 다른 파일에서 import해서 사용할 수 있게 만드는 것.
// default는 "이 파일의 기본 export 대상은 App이다" 라는 뜻 
export default App;
