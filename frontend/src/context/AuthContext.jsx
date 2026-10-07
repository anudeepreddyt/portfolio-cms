import {createContext,useContext,useMemo,useState} from 'react';
import {api,setTokens,clearTokens} from '../services/api';
const AuthContext=createContext(null);
export function AuthProvider({children}){const [loggedIn,setLoggedIn]=useState(!!localStorage.getItem('accessToken')); const login=async(email,password)=>{const t=await api.post('/auth/login',{email,password});setTokens(t.accessToken,t.refreshToken);setLoggedIn(true);}; const logout=()=>{clearTokens();setLoggedIn(false)}; return <AuthContext.Provider value={useMemo(()=>({loggedIn,login,logout}),[loggedIn])}>{children}</AuthContext.Provider>}
export const useAuth=()=>useContext(AuthContext);
