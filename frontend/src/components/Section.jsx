export default function Section({title,children,action}){return <section className="section container"><div className="section-head"><h2>{title}</h2>{action}</div>{children}</section>}
