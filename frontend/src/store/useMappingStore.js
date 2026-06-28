import { create } from 'zustand';

const useMappingStore = create((set) => ({
  pendingMappings: [],
  approvedMappings: [],

  addPendingMapping: (mapping) => set((state) => ({
    pendingMappings: [...state.pendingMappings, mapping]
  })),

  approveMapping: (id) => set((state) => {
    const mappingToApprove = state.pendingMappings.find(m => m.id === id);
    if (!mappingToApprove) return state;

    return {
      pendingMappings: state.pendingMappings.filter(m => m.id !== id),
      approvedMappings: [...state.approvedMappings, mappingToApprove]
    };
  }),

  rejectMapping: (id) => set((state) => ({
    pendingMappings: state.pendingMappings.filter(m => m.id !== id)
  })),
}));

export default useMappingStore;
