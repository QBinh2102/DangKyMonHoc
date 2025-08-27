'use client'
import "./hocphi.css"

const HocPhi = () => {
    return (
        <div>
            <h1 className="text-center mt-3 mb-3">Học phí của sinh viên</h1>

            <div>
                <table className="table table-bordered table-hocphi">
                    <thead>
                        <tr>
                            <th>Học kỳ</th>
                            <th>Học phí</th>
                            <th>Trạng thái</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    );
}
export default HocPhi;