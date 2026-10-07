const BASE=(import.meta.env.VITE_API_BASE_URL||'').replace(/\/$/,'');
if(!BASE) throw new Error('VITE_API_BASE_URL is not configured.');
let accessToken=localStorage.getItem('accessToken');
export function setTokens(a,r){accessToken=a; if(a)localStorage.setItem('accessToken',a);else localStorage.removeItem('accessToken'); if(r)localStorage.setItem('refreshToken',r);}
export function clearTokens(){accessToken=null;localStorage.removeItem('accessToken');localStorage.removeItem('refreshToken');}
async function request(path,options={}){
  const headers=new Headers(options.headers||{}); if(options.body && !(options.body instanceof FormData)) headers.set('Content-Type','application/json'); if(accessToken)headers.set('Authorization',`Bearer ${accessToken}`);
  let res=await fetch(`${BASE}${path}`,{...options,headers});
  if(res.status===401 && localStorage.getItem('refreshToken')){const rr=await fetch(`${BASE}/auth/refresh`,{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({refreshToken:localStorage.getItem('refreshToken')})});if(rr.ok){const t=await rr.json();setTokens(t.accessToken,t.refreshToken);headers.set('Authorization',`Bearer ${t.accessToken}`);res=await fetch(`${BASE}${path}`,{...options,headers});}}
  const text=await res.text(); let data={}; try{data=text?JSON.parse(text):{}}catch{data={message:text}} if(!res.ok)throw new Error(data.message||'Request failed'); return data;
}
export const api={get:(p)=>request(p),post:(p,b)=>request(p,{method:'POST',body:b instanceof FormData?b:JSON.stringify(b)}),put:(p,b)=>request(p,{method:'PUT',body:JSON.stringify(b)}),patch:(p,b)=>request(p,{method:'PATCH',body:JSON.stringify(b)}),del:(p)=>request(p,{method:'DELETE'})};
export {BASE};
