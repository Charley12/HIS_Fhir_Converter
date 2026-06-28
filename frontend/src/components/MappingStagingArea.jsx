import React from 'react';

const MappingStagingArea = () => {
  return (
    <div className="h-full bg-white p-4 overflow-y-auto">
      <h2 className="text-lg font-semibold mb-4">Pending Mappings</h2>
      <div className="space-y-4">
        {/* Placeholder mapping card */}
        <div className="border rounded-lg p-4 shadow-sm">
          <div className="flex justify-between items-center mb-2">
            <span className="font-mono bg-slate-100 px-2 py-1 rounded text-sm">Source: local_code_A</span>
            <span className="text-slate-400">➔</span>
            <span className="font-mono bg-green-50 text-green-700 px-2 py-1 rounded text-sm">Target: LOINC 12345-6</span>
          </div>
          <div className="flex justify-end space-x-2 mt-4">
            <button className="px-3 py-1 text-sm border rounded hover:bg-slate-50">Modify</button>
            <button className="px-3 py-1 text-sm bg-blue-600 text-white rounded hover:bg-blue-700">Approve</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MappingStagingArea;
