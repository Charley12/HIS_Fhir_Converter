import React from 'react';
import ChatWindow from './components/ChatWindow';
import MappingStagingArea from './components/MappingStagingArea';
import FHIRTreeViewer from './components/FHIRTreeViewer';

function App() {
  return (
    <div className="flex h-screen bg-slate-100 overflow-hidden">
      {/* Left Panel: Chat */}
      <div className="w-1/3 min-w-[300px]">
        <ChatWindow />
      </div>

      {/* Right Panel: Split View */}
      <div className="flex-1 flex flex-col">
        <div className="h-1/2">
          <MappingStagingArea />
        </div>
        <div className="h-1/2">
          <FHIRTreeViewer />
        </div>
      </div>
    </div>
  );
}

export default App;
