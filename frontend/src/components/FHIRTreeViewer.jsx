import React, { useState } from 'react';
import { ChevronRight, ChevronDown } from 'lucide-react';

const TreeNode = ({ label, children }) => {
  const [isOpen, setIsOpen] = useState(true);

  return (
    <div className="ml-4">
      <div
        className="flex items-center cursor-pointer py-1 hover:bg-slate-50"
        onClick={() => setIsOpen(!isOpen)}
      >
        {children ? (
          isOpen ? <ChevronDown size={16} /> : <ChevronRight size={16} />
        ) : (
          <span className="w-4" />
        )}
        <span className="ml-1 text-sm font-mono">{label}</span>
      </div>
      {isOpen && children && (
        <div className="border-l ml-2 pl-2">
          {children}
        </div>
      )}
    </div>
  );
};

const FHIRTreeViewer = () => {
  return (
    <div className="h-full bg-slate-50 border-t p-4 overflow-y-auto">
      <h2 className="text-sm font-semibold text-slate-500 mb-2 uppercase tracking-wider">Target FHIR Structure</h2>
      <div className="bg-white border rounded-lg p-2">
        <TreeNode label="Observation">
          <TreeNode label="status: final" />
          <TreeNode label="code">
            <TreeNode label="coding">
              <TreeNode label="system: http://loinc.org" />
              <TreeNode label="code: 85354-9" />
            </TreeNode>
          </TreeNode>
        </TreeNode>
      </div>
    </div>
  );
};

export default FHIRTreeViewer;
