'use client'

import { useState } from "react";

const ThemBuoiHoc = () =>{

    const info = [{

    }];
    const [buoiHoc, setBuoiHoc] = useState({});
    const [loading, setLoading] = useState(false);
    const [msg, setMsg] = useState("");

    return (
        <div>
            <div className="text-center mt-5 mb-5">
                <h1>THÊM BUỔI HỌC</h1>
            </div>

            {msg && (
                msg.includes("thành công") ? (
                    <div className="alert alert-success text-center" role="alert">
                        {msg}
                    </div>
                ):(
                    <div className="alert alert-warning text-center" role="alert">
                        {msg}
                    </div>
                )
            )}

            <div>
                
            </div>
        </div>
    );
}
export default ThemBuoiHoc;