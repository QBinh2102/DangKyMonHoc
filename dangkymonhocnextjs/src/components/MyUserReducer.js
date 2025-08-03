'use client';

import { MyDispatchContext, MyUserContext } from '@/configs/Contexts';
import { useReducer } from 'react';
import cookie from 'react-cookies';

export default function AppProvider({ children }) {
  const reducer = (state, action) => {
    switch (action.type) {
        case "login":
            return action.payload;
        case "logout":
            cookie.remove('token');
            return null;
    }
    return current;
  };

  const [state, dispatch] = useReducer(reducer, { user: null });

  return (
    <MyUserContext.Provider value={state}>
      <MyDispatchContext.Provider value={dispatch}>
        {children}
      </MyDispatchContext.Provider>
    </MyUserContext.Provider>
  );
}
