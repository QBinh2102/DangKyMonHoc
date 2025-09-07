'use client'

import { authApis, endpoints } from "@/configs/Apis";
import { useState } from "react";

const ThemQuyDinh = () => {

    const info = [{
        label: "Quy định",
        field: "ten",
        type: "text",
    }, {
        label: "Giá trị",
        field: "giaTri",
        type: "number",
        min: 0,
    }];
    const [newQuyDinh, setNewQuyDinh] = useState({});
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    const addQuyDinh = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            await authApis().post(endpoints['themQuyDinh'], newQuyDinh);
            setNewQuyDinh({});
            setMsg("Thêm quy định thành công!");
        } catch (ex) {
            console.error(ex);
            setMsg("Thêm quy định thất bại!");
        } finally {
            setLoading(false);
        }
    }

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM QUY ĐỊNH</h1>
            </div>

            {msg && (
                msg.includes("thành công") ? (
                    <div className="alert alert-success text-center" role="alert">
                        {msg}
                    </div>
                ) : (
                    <div className="alert alert-warning text-center" role="alert">
                        {msg}
                    </div>
                )
            )}
            
            <div>
                <form onSubmit={addQuyDinh} className="w-50 mx-auto">
                    {info.map(i => (
                        <div className="mb-3 mt-3" key={i.field}>
                            <label htmlFor={i.field} className="form-label">{i.label}</label>
                            <input
                                id={i.field}
                                className="form-control"
                                type={i.type}
                                value={newQuyDinh[i.field] || ''}
                                min={i.min !== undefined ? i.min : undefined}
                                onChange={e => setNewQuyDinh({ ...newQuyDinh, [i.field]: e.target.value })}
                                placeholder={i.label}
                                required
                            />
                        </div>
                    ))}
                    <div className="text-center">
                        <button type="submit" className="text-center btn btn-primary mb-3" disabled={loading}>
                            {loading ?
                                <>
                                    <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                    Đang thêm...
                                </> : "Thêm"}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default ThemQuyDinh;